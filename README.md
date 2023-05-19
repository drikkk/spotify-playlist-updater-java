# spotify-playlist-updater

I created this project to fight the Spotify playlist reporting bots.
Salty bots spam reports on growing playlists which trigger the Spotifys playlist nulling automation and the playlist is wiped from its title, description and image.

With this program you can restore your playlists easily and stick it to the bots!

# How to use
Rename config.example.properties to config.properties and fill it with your Spotify API credentials.
You will need the Refresher Bearer Token, Client ID, Secret Key and code.

After those are set you can define your Playlist in the MyPlaylists file by initiating a new Playlist object.

Once its defined, run the PlaylistUpdate.fixMyPlaylist() method against your Playlist object.
