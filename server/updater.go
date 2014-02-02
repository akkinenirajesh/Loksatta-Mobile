package main

import (
	"encoding/json"
	"github.com/huandu/facebook"
	"labix.org/v2/mgo"
	"labix.org/v2/mgo/bson"
	"log"
	"net/http"
	"time"
)

type FacebookSource struct {
	Id            bson.ObjectId "_id,omitempty"
	Handle        string
	FacebookId    string
	LastCheckedAt time.Time
}
type Updater struct {
	conf    *Config
	session *mgo.Session
}

func (updater *Updater) updateSourcesFromConfig() (map[string]*FacebookSource, error) {
	session := updater.session.New()
	c := session.DB("lsp").C("facebook_sources")

	result := []FacebookSource{}

	log.Println("Getting Existing Sources from Database")
	iter := c.Find(nil).Iter()
	err := iter.All(&result)
	if err != nil {
		log.Println(err)
		return nil, err
	}
	m := make(map[string]*FacebookSource)
	// Add all results into map
	for _, s := range result {
		log.Println("Existing Facebook Sources ", s)
		copy := s
		m[s.Handle] = &copy
	}
	// Check if we have all new sources.
	for _, follow := range updater.conf.Facebook.Follow {
		_, ok := m[follow]
		if !ok {
			log.Println("Not found. Creating New Facebook Source:", follow)
			s, err := updater.AddNewFacebookSource(c, follow)
			if err != nil {
				return nil, err
			}
			m[follow] = s
		}
	}
	//TODO need to remove old unwanted sources
	return m, nil
}
func (updater *Updater) AddNewFacebookSource(c *mgo.Collection, id string) (*FacebookSource, error) {
	log.Println("Fetching Facebook ID for handle:", id)
	resp, err := http.Get("https://graph.facebook.com/" + id)
	if err != nil {
		log.Println(err)
		return nil, err
	}
	// Decode the request and take the ID
	var i struct {
		Id string `json:"id"`
	}
	dec := json.NewDecoder(resp.Body)
	err = dec.Decode(&i)
	if err != nil {
		log.Println(err)
		return nil, err
	}
	log.Println("Got Facebook ID for handle ", id, i.Id)
	// Create new source
	source := FacebookSource{}
	source.Id = bson.NewObjectId()
	source.Handle = id
	source.FacebookId = i.Id

	log.Println("Saving New Source into Database ", id)
	// save the new source to database
	err = c.Insert(&source)
	if err != nil {
		log.Println(err)
		return nil, err
	}

	return &source, nil
}

func (updater *Updater) updateFromSource(source *FacebookSource) {
	var fb = facebook.New(updater.conf.Facebook.AppId, updater.conf.Facebook.AppSecret)
	res, _ := facebook.Get("/"+source.FacebookId+"/posts?limit=1", facebook.Params{
		"access_token": fb.AppAccessToken(),
	})
	var r struct {
		Data []struct {
			Id          string
			Message     string
			Caption     string
			Type        string
			CreatedTime string
			UpdatedTime string
			Shares      struct {
				Count int32
			}
		}
	}
	err := res.Decode(&r)
	if err != nil {
		log.Println(err)
		return
	}

	for _, post := range r.Data {
		log.Println("Post ID :", post.Id)
		log.Println("Post Message :", post.Message)
		log.Println("Post Caption :", post.Caption)
		log.Println("Post Type :", post.Type)
		log.Println("Created Time :", post.CreatedTime)
		log.Println("Update Time:", post.UpdatedTime)
		log.Println("Likes Count:", updater.GetFacebookCount("likes", post.Id))
		log.Println("Shares Count:", post.Shares.Count)
		log.Println("Comments Count:", updater.GetFacebookCount("comments", post.Id))

		switch post.Type {
		case "status":
			//updater.CreateOrUpdateStatus(post)
		case "photo":
			//updater.CreateOrUpdatePhoto(post)
		case "video":
			//updater.CreateOrUpdateVideo(post)
		case "event":
			//updater.CreateOrUpdateEvent(post)
		}
		//TODO check if post is in the database
		//
	}
}

func (updater *Updater) CreateOrUpdateStatus(t string, id string) {

}
func (updater *Updater) GetFacebookCount(t string, id string) int32 {
	var fb = facebook.New(updater.conf.Facebook.AppId, updater.conf.Facebook.AppSecret)
	res, _ := facebook.Get("/"+id+"/"+t+"?limit=0&summary=1", facebook.Params{
		"access_token": fb.AppAccessToken(),
	})
	var r struct {
		Summary struct {
			TotalCount int32
		}
	}
	res.Decode(&r)
	return r.Summary.TotalCount
}

func (updater *Updater) ProcessFacebookPost(post map[string]interface{}) {
	updater.session.DB("lsp").C("feed")
}

func (updater *Updater) DownloadFacebookPhotos(id string) {

}

func Update(conf *Config) {
	log.Println("Started Updater")
	updater := Updater{}
	updater.conf = conf

	//"mongodb://lsptest:lsptest@troup.mongohq.com:10051/lsp"
	log.Println("Connecting to Database ", conf.DatabaseUrl)
	session, err := mgo.Dial(conf.DatabaseUrl)
	if err != nil {
		log.Println(err)
	}

	updater.session = session
	log.Println("Updating Sources")
	sources, err := updater.updateSourcesFromConfig()
	log.Println("Finished Updating Sources")
	for key, source := range sources {
		log.Println("Fetching Updates from source ", key)
		updater.updateFromSource(source)
		log.Println("Finished Updates from source ", key)
	}
	log.Println("Updater Finished")
}
