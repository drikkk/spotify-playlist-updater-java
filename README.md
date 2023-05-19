# Spotify Playlist Updater

I created this project to fight the Spotify playlist reporting bots (Takedown Notification).
Salty creators use bots to spam reports on growing playlists which triggers the Takedown Notification and your playlist is wiped from its title, description and image.

With this program you can restore your playlists easily and stick it to the bots!

## Prerequisites
- Java SDK 11
- Maven 3
- Spotify API Credentials (with ugc-image-upload & playlist-modify-*) permissions

## How to use
- Rename config.example.properties to config.properties
- Add your Spotify credentials to config.properties
- Define your playlists in MyPlaylists.class 
- Execute main class through IDE or with Maven: `mvn -f C:\path\to\project\root\pom.xml exec:java -Dexec.mainClass="fix.my.playlist.Main"`
