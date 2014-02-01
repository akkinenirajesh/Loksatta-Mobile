package main

type Source struct {
}
type Updater struct {
	conf *Config
}

func (updater *Updater) updateSourcesFromConfig() {

}

func (updater *Updater) getSources() []*Source {
	return nil
}

func (updater *Updater) updateFromSource(source *Source) {

}
/*
Get the likes of given FB node and saves it in database
If we have already saved this node before, then we fetch likes from reverse to avoid wasting requests
*/
func updateFacebookLikes(node string) {

}
/*
Get the likes of given FB node and saves it in database
If we have already saved this node before, then we fetch likes from reverse to avoid wasting requests
*/
func updateFacebookComments(node string) {

}

func GetPostType(){

}
func DownloadFacebookPhotos(){

}


func update(conf *Config) {
	updater := Updater{conf}

	updater.updateSourcesFromConfig()
	sources := updater.getSources()
	for _, source := range sources {
		updater.updateFromSource(source)
	}
}
