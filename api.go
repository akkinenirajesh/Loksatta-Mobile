package main

import (
    "net/http"
)


// This handler will now serve HTTP, HTTPS, and SPDY requests.
func (*Server) HomeHandler(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}
// This handler will now serve HTTP, HTTPS, and SPDY requests.
func (*Server) Register(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}
func (*Server) UpdateUserDetails(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}
func (*Server) Feed(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}
func (*Server) FeedSince(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}
func (*Server) GalleryPhotos(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}

func (*Server) GalleryPhotosSince(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}

func (*Server) GalleryVideos(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}

func (*Server) GalleryVideosSince(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}

func (*Server) Events(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}

func (*Server) EventsSince(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}

func (*Server) Leaders(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}