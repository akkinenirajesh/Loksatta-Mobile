package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"labix.org/v2/mgo/bson"
	"log"
	"net/http"
	"strconv"
	"time"
)

const (
	Hour      time.Duration = time.Hour
	Day                     = Hour * 24
	Month                   = Day * 30
	SixMonths               = Month * 6
)

type RegistrationResp struct {
	Id string
}

// This handler will now serve HTTP, HTTPS, and SPDY requests.
func (server *Server) Register(w http.ResponseWriter, r *http.Request) {
	session := server.db.New()
	c := session.DB("lsp").C("users")
	w.Header().Set("Content-Type", "application/json")

	user := User{}
	user.Id = bson.NewObjectId()
	err := c.Insert(&user)
	if err != nil {
		log.Println(err)
	}
	resp := RegistrationResp{}
	resp.Id = user.Id.Hex()
	enc := json.NewEncoder(w)
	if err := enc.Encode(&resp); err != nil {
		log.Println(err)
	}
	session.Close()
}
func (server *Server) UpdateUserDetails(w http.ResponseWriter, r *http.Request) {
	decoder := json.NewDecoder(r.Body)
	var u User
	err := decoder.Decode(&u)
	if err != nil {
		http.Error(w, "Invalid Request", http.StatusBadRequest)
	}
	session := server.db.New()
	c := session.DB("lsp").C("users")

	err = c.Update(bson.M{"_id": u.Id}, &u)
	if err != nil {
		log.Println(err)
	}
	w.Write([]byte("Success"))
	session.Close()
}
func (server *Server) Serve(w http.ResponseWriter, r *http.Request, resultType string, result interface{}, collection string, since time.Time) {
	w.Header().Set("Content-Type", "application/json")

	session := server.db.New()
	c := session.DB("lsp").C(collection)
	//bson.M{"UpdatedAt": bson.M{"$lt": 10}}

	selector := bson.M{"updatedat": bson.M{"$gt": &since}}

	iter := c.Find(selector).Limit(500).Iter()
	err := iter.All(result)
	if err != nil {
		log.Println(err)
		return
	}
	enc := json.NewEncoder(w)
	if err := enc.Encode(&bson.M{resultType: result}); err != nil {
		log.Println(err)
	}
	log.Println(time.Now())
}
func ParseSince(r *http.Request) (time.Time, error) {
	since := mux.Vars(r)["date"]
	switch since {
	case "":
		return time.Now().Add(-SixMonths), nil
	default:
		_, err := strconv.Atoi(since)
		if err != nil {
			return time.Now(), err
		}
		return time.Now(), nil
	}
}
func (server *Server) Feed(w http.ResponseWriter, r *http.Request) {
	result := []Feed{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
	server.Serve(w, r, "posts", &result, "feed", since)
}
func (server *Server) GalleryPhotos(w http.ResponseWriter, r *http.Request) {
	result := []Photo{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
	server.Serve(w, r, "photos", &result, "photos", since)
}

func (server *Server) GalleryVideos(w http.ResponseWriter, r *http.Request) {
	result := []Video{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
	server.Serve(w, r, "videos", &result, "videos", since)
}

func (server *Server) Events(w http.ResponseWriter, r *http.Request) {
	result := []Event{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
	server.Serve(w, r, "events", &result, "events", since)
}

func (server *Server) Leaders(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}
