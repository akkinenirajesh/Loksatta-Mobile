package main

import (
    "net/http"
)

func (*Server) AdminLogin(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Hello, HTTP!"))
}
