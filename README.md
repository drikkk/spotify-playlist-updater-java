# Spotify Playlist Updater

I created this project to fight the Spotify playlist reporting bots (Takedown Notification).
Salty creators use bots to spam reports on growing playlists which triggers the Takedown Notification and your playlist is wiped from its title, description and image.

## Prerequisites
- [Java SDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org/download.cgi)
- [Spotify API Credentials](https://developer.spotify.com/documentation/web-api/tutorials/client-credentials-flow)

## How to use
- Clone repository to "C:\\"
- Rename config.properties.example to config.properties
- Add your Spotify API credentials to config.properties

## Define your playlists to update
- Get your playlist ID: ```https://open.spotify.com/playlist/YOUR_PLAYLIST_ID?si=83ebb0218ab843cd```
- Navigate to: C:\spotify-playlist-updater\playlists
- Move all your playlist images here
- Edit playlists.json and configure your playlist name, description, image name and Spotify ID, like so:
```
{
    "name": "Example playlist",
    "description": "Example description",
    "image": "example_image.jpg",
    "spotifyPlaylistId": "EXAMPLE_ID"
},
{
    "name": "Cool playlist",
    "description": "My first cool playlist",
    "image": "",
    "spotifyPlaylistId": "6IOBfN7LWZJ8IGfX7i07R1"
}
```
Note: For "Cool playlist" the image will not be updated since we leave it at empty value.

## Running the code in the background on your Windows machine
Open Task Scheduler and create a new task to run a program with admin permissions, schedule it to 5 minutes interval after first run.
```
Program to start: C:\Windows\System32\wscript.exe
Argument: "C:\spotify-playlist-updater\src\main\resources\runner.vbs"
Start in: C:\spotify-playlist-updater\src\main\resources
```
