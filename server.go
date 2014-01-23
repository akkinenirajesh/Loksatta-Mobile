package main

import (
    "github.com/SlyMarbo/spdy" // Import SPDY.
    "labix.org/v2/mgo"
    "github.com/huandu/facebook"
    "github.com/gorilla/mux"
    "fmt"
)

type Server struct{}

func SetUpRoutes(server *Server){


    r := mux.NewRouter()
    s := r.PathPrefix("/api/v1/").Subrouter()
    // API Handlers
    s.HandleFunc("/register", server.Register);
    s.HandleFunc("/update", server.UpdateUserDetails);
    s.HandleFunc("/feed", server.Feed);
    s.HandleFunc("/feed/since/{date}", server.FeedSince);
    s.HandleFunc("/feed/since/{date}", server.FeedSince);
    s.HandleFunc("/gallery/photos", server.GalleryPhotos);
    s.HandleFunc("/gallery/photos/since/{date}", server.GalleryPhotosSince);
    s.HandleFunc("/gallery/videos/", server.GalleryVideos);
    s.HandleFunc("/gallery/videos/since/{date}", server.GalleryVideosSince);
    s.HandleFunc("/events", server.Events);
    s.HandleFunc("/events/since/{date}", server.Events);
    s.HandleFunc("/leaders/{state}/", server.Leaders);
    s.HandleFunc("/leaders/{state}/{district}/", server.Leaders);
    s.HandleFunc("/leaders/{state}/{district}/{const}", server.Leaders);

    //Admin Section
    a := r.PathPrefix("/admin/").Subrouter()
    a.HandleFunc("/login", server.AdminLogin);
}

func main() {
    server := Server{}
    SetUpRoutes(&server)
    
    // Use spdy's ListenAndServe.
    err := spdy.ListenAndServeTLS("localhost:443", "cert.pem", "key.pem", nil)
    if err != nil {
        // handle error.
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
    mgo.Dial("url")
}