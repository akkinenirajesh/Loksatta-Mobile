package main

import (
	"encoding/json"
	"github.com/nu7hatch/gouuid"
	"log"
	"net/http"
)

type RegistrationResp struct {
	Guid string
}

// This handler will now serve HTTP, HTTPS, and SPDY requests.
func (server *Server) Register(w http.ResponseWriter, r *http.Request) {
	session := server.db.New()
	c := session.DB("lsp").C("users")
	w.Header().Set("Content-Type", "application/json")

	id, err := uuid.NewV4()
	user := User{}
	user.id = id.String()
	err = c.Insert(user)
	if err != nil {
		log.Println(err)
	}
	resp := RegistrationResp{}
	resp.Guid = user.id
	enc := json.NewEncoder(w)
	if err := enc.Encode(&resp); err != nil {
		log.Println(err)
	}
	session.Close()
}
func (server *Server) UpdateUserDetails(w http.ResponseWriter, r *http.Request) {
	session := server.db.New()
	c := session.DB("lsp").C("users")
	w.Header().Set("Content-Type", "application/json")

	id, err := uuid.NewV4()
	user := User{}
	user.id = id.String()
	err = c.Insert(user)
	if err != nil {
		log.Println(err)
	}
	resp := RegistrationResp{}
	resp.Guid = user.id
	enc := json.NewEncoder(w)
	if err := enc.Encode(&resp); err != nil {
		log.Println(err)
	}
	session.Close()
}
func (server *Server) Feed(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}
func (server *Server) FeedSince(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}
func (server *Server) GalleryPhotos(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}

func (server *Server) GalleryPhotosSince(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}

func (server *Server) GalleryVideos(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}

func (server *Server) GalleryVideosSince(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}

func (server *Server) Events(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}

func (server *Server) EventsSince(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}

func (server *Server) Leaders(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Hello, HTTP!"))
}
