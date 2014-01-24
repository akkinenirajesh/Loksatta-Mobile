package main

import (
	"fmt"
	"github.com/SlyMarbo/spdy" // Import SPDY.
	"github.com/gorilla/mux"
	"github.com/huandu/facebook"
	"labix.org/v2/mgo"
	"log"
	"net/http"
)

type Server struct {
	db *mgo.Session
}

type User struct {
	id    string
	name  string
	email string
}

func SetUpRoutes(server *Server) {

	r := mux.NewRouter()
	s := r.PathPrefix("/api/v1/").Subrouter()
	// API Handlers
	s.HandleFunc("/register", server.Register)
	s.HandleFunc("/update", server.UpdateUserDetails)
	s.HandleFunc("/feed", server.Feed)
	s.HandleFunc("/feed/since/{date}", server.FeedSince)
	s.HandleFunc("/feed/since/{date}", server.FeedSince)
	s.HandleFunc("/gallery/photos", server.GalleryPhotos)
	s.HandleFunc("/gallery/photos/since/{date}", server.GalleryPhotosSince)
	s.HandleFunc("/gallery/videos/", server.GalleryVideos)
	s.HandleFunc("/gallery/videos/since/{date}", server.GalleryVideosSince)
	s.HandleFunc("/events", server.Events)
	s.HandleFunc("/events/since/{date}", server.Events)
	s.HandleFunc("/leaders/{state}/", server.Leaders)
	s.HandleFunc("/leaders/{state}/{district}/", server.Leaders)
	s.HandleFunc("/leaders/{state}/{district}/{const}", server.Leaders)

	//Admin Section
	a := r.PathPrefix("/admin/").Subrouter()
	a.HandleFunc("/login", server.AdminLogin)

	http.Handle("/", r)
}

func main() {
	server := Server{}

	session, err := mgo.Dial("mongodb://lsptest:lsptest@troup.mongohq.com:10051/lsp")
	if err != nil {
		log.Println(err)
	}

	server.db = session

	SetUpRoutes(&server)

	{
		// Use spdy's ListenAndServe.
		err := spdy.ListenAndServeTLS("localhost:443", "cert.pem", "key.pem", nil)
		if err != nil {
			// handle error.
			//log.Println(err)
			http.ListenAndServe(":8080", nil)
		}
	}

	res, _ := facebook.Get("/akkineni.rajesh", nil)
	fmt.Println("my facebook id is", res["id"])
	// s := &http.Server{
	//     Addr:           ":8080",
	//     Handler:        myHandler,
	//     ReadTimeout:    10 * time.Second,
	//     WriteTimeout:   10 * time.Second,
	//     MaxHeaderBytes: 1 << 20,
	// }
	// log.Fatal(s.ListenAndServe())

}
