package main

import (
	"encoding/json"
	"errors"
	"github.com/gorilla/mux"
	"labix.org/v2/mgo"
	"labix.org/v2/mgo/bson"
	"log"
	"net/http"
	"os"
	"strconv"
	"time"
)

type Config struct {
	DatabaseUrl string
	ImagesPath  string
	Facebook    FacebookConfig
	Google      GoogleConfig
}
type FacebookConfig struct {
	AppId     string
	AppSecret string
	Follow    []string
}

type GoogleConfig struct {
	AppId     string
	AppSecret string
	Follow    []string
}

type Server struct {
	db     *mgo.Session
	config *Config
}

type GPSPosition struct {
	Latitude  float64
	Longitude float64
}

type User struct {
	Id                 bson.ObjectId "_id,omitempty"
	Name               string
	Email              string
	MembershipId       string
	State              string
	District           string
	Constituency       string
	EventNotifications bool
	NotificationRadius int8
	Locaiton           GPSPosition
	FacebookId         string
	TwitterId          string
	GPlusId            string
}
type Feed struct {
	Id            bson.ObjectId "_id,omitempty"
	AuthorName    string
	AuthorId      string
	CreatedAt     time.Time
	UpdatedAt     time.Time
	Title         string
	Summary       string
	Content       string
	Link          string
	FacebookId    string
	GPlusId       string
	LikesCount    int32
	ShareCount    int32
	CommentsCount int32
	Images        []string
}
type Photo struct {
	Id            bson.ObjectId "_id,omitempty"
	AuthorName    string
	AuthorId      string
	CreatedAt     time.Time
	UpdatedAt     time.Time
	Title         string
	FacebookId    string
	GPlusId       string
	LikesCount    int32
	ShareCount    int32
	CommentsCount int32
}
type Video struct {
	Id            bson.ObjectId "_id,omitempty"
	AuthorName    string
	AuthorId      string
	CreatedAt     time.Time
	UpdatedAt     time.Time
	Title         string
	YouTubeId     string
	FacebookId    string
	GPlusId       string
	LikesCount    int32
	ShareCount    int32
	CommentsCount int32
}
type Event struct {
	Id             bson.ObjectId "_id,omitempty"
	CreatedAt      time.Time
	UpdatedAt      time.Time
	Title          string
	Details        string
	When           time.Time
	Locaiton       GPSPosition
	FacebookId     string
	GPlusId        string
	LikesCount     int32
	ShareCount     int32
	CommentsCount  int32
	AttendingCount int32
}
type Leader struct {
	Id         bson.ObjectId "_id,omitempty"
	Name       string
	Position   string
	CreatedAt  time.Time
	UpdatedAt  time.Time
	Link       string
	FacebookId string
	GPlusId    string
	TwitterId  string
	Email      string
	phone      string
}

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
}
func ParseSince(r *http.Request) (time.Time, error) {
	since := mux.Vars(r)["date"]
	switch since {
	case "":
		return time.Now().Add(-SixMonths), nil
	default:
		if len(since) != 12 {
			return time.Now(), errors.New("Invalid Date Format")
		}
		year, err := strconv.Atoi(since[0:4])
		if err != nil {
			return time.Now(), err
		}
		monthVal, err := strconv.Atoi(since[4:6])
		if err != nil {
			return time.Now(), err
		}
		month := time.Month(monthVal)
		day, err := strconv.Atoi(since[6:8])
		if err != nil {
			return time.Now(), err
		}
		hour, err := strconv.Atoi(since[8:10])
		if err != nil {
			return time.Now(), err
		}
		min, err := strconv.Atoi(since[10:12])
		if err != nil {
			return time.Now(), err
		}
		s := time.Date(year, month, day, hour, min, 0, 0, time.UTC)
		log.Println(s)
		return s, nil
	}
}
func (server *Server) Feed(w http.ResponseWriter, r *http.Request) {
	result := []Feed{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
		return
	}
	server.Serve(w, r, "posts", &result, "feed", since)
}
func (server *Server) GalleryPhotos(w http.ResponseWriter, r *http.Request) {
	result := []Photo{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
		return
	}
	server.Serve(w, r, "photos", &result, "photos", since)
}

func (server *Server) GalleryVideos(w http.ResponseWriter, r *http.Request) {
	result := []Video{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
		return
	}
	server.Serve(w, r, "videos", &result, "videos", since)
}

func (server *Server) Events(w http.ResponseWriter, r *http.Request) {
	result := []Event{}
	since, err := ParseSince(r)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
		return
	}
	server.Serve(w, r, "events", &result, "events", since)
}

func (server *Server) Leaders(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	session := server.db.New()
	c := session.DB("lsp").C("leaders")
	//bson.M{"UpdatedAt": bson.M{"$lt": 10}}

	selector := bson.M{}
	state := mux.Vars(r)["state"]
	if state != "" {
		selector["state"] = state
	}
	district := mux.Vars(r)["district"]
	if district != "" {
		selector["district"] = district
	}
	constituency := mux.Vars(r)["constituency"]
	if constituency != "" {
		selector["constituency"] = constituency
	}
	result := []Leader{}

	iter := c.Find(selector).Limit(500).Iter()
	err := iter.All(&result)
	if err != nil {
		log.Println(err)
		return
	}
	enc := json.NewEncoder(w)
	if err := enc.Encode(&bson.M{"leaders": &result}); err != nil {
		log.Println(err)
	}
}

func SetUpRoutes(server *Server) {

	r := mux.NewRouter()
	s := r.PathPrefix("/api/v1/").Subrouter()
	// API Handlers
	s.HandleFunc("/register", server.Register)
	s.HandleFunc("/update", server.UpdateUserDetails).Methods("POST")
	s.HandleFunc("/feed", server.Feed)
	s.HandleFunc("/feed/since/{date}", server.Feed)
	s.HandleFunc("/gallery/photos", server.GalleryPhotos)
	s.HandleFunc("/gallery/photos/since/{date}", server.GalleryPhotos)
	s.HandleFunc("/gallery/videos", server.GalleryVideos)
	s.HandleFunc("/gallery/videos/since/{date}", server.GalleryVideos)
	s.HandleFunc("/events", server.Events)
	s.HandleFunc("/events/since/{date}", server.Events)
	s.HandleFunc("/leaders/{state}/", server.Leaders)
	s.HandleFunc("/leaders/{state}/{district}/", server.Leaders)
	s.HandleFunc("/leaders/{state}/{district}/{const}", server.Leaders)

	http.Handle("/images/", http.StripPrefix("/images/", http.FileServer(http.Dir(server.config.ImagesPath))))

	//Admin Section
	r.PathPrefix("/admin/").Subrouter()
	//a.HandleFunc("/login", server.AdminLogin)

	http.Handle("/", r)
}

func main() {

	var config Config
	// read Config
	configFile, err := os.Open("config.json")
	if err != nil {
		log.Println("opening config file", err.Error())
		os.Exit(1)
	}

	jsonParser := json.NewDecoder(configFile)
	if err = jsonParser.Decode(&config); err != nil {
		log.Println("parsing config file", err.Error())
		os.Exit(1)
	}

	if os.Args[1] == "update" {
		Update(&config)
	} else {
		StartServer(&config)
	}
}
func StartServer(config *Config) {
	server := Server{}
	server.config = config
	//"mongodb://lsptest:lsptest@troup.mongohq.com:10051/lsp"
	session, err := mgo.Dial(config.DatabaseUrl)
	if err != nil {
		log.Println(err)
	}

	server.db = session

	SetUpRoutes(&server)

	http.ListenAndServe(":8080", nil)
}
