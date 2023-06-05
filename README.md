# Deprecated & ported to Python: [Spotify Playlist Doctor](https://github.com/drikkk/Spotify-Playlist-Doctor)



## Spotify Playlist Updater

I created this project to fight the Spotify playlist reporting bots.
Salty creators use bots to spam reports on growing playlists which triggers the Spotify Takedown Notification and your
playlist will be nulled from its title, description and image.

I have the runner set up on my Windows 11 machine like so:

## Prerequisites

- [Java SDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org/download.cgi)
- [Spotify API Credentials](https://developer.spotify.com/documentation/web-api/tutorials/client-credentials-flow)

## Set up Java project

- Clone repository to "C:\\"
- Rename config.properties.example to config.properties
- Add your Spotify API credentials to config.properties

## Define your playlists

- Get your playlist ID: ```https://open.spotify.com/playlist/YOUR_PLAYLIST_ID?si=83ebb0218ab843cd```
- Move all your playlist images to: ```C:\spotify-playlist-updater\playlists```
- Edit ```C:\spotify-playlist-updater\playlists\playlists.json```
- Configure your playlist name, description, image name and Spotify ID, like so:

```
{
    "name": "Example playlist",
    "description": "Example description",
    "image": "example_image.jpg",
    "spotifyPlaylistId": "EXAMPLE_ID"
},
{
    "name": "Playlist without description and image",
    "description": "",
    "image": "",
    "spotifyPlaylistId": "6IOBfN7LWZJ8IGfX7i07R1"
}
```

## Running every 5 minutes in the background on your Windows machine

Open Task Scheduler and create a new task to run a program with admin permissions, schedule it to 5 minutes interval
after first run.

```
Program to start: C:\Windows\System32\wscript.exe
Argument: "C:\spotify-playlist-updater\runner.vbs"
Start in: C:\spotify-playlist-updater
```

If you have followed all the steps without errors then your playlists will now be checked in the background every 5
minutes and will be updated once playlist name change is detected!
