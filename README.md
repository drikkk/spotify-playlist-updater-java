# Spotify Playlist Updater

I created this project to fight the Spotify playlist reporting bots (Takedown Notification).
Salty creators use bots to spam reports on growing playlists which triggers the Takedown Notification and your playlist is wiped from its title, description and image.

With this program you can restore your playlists easily and stick it to the bots!

## Prerequisites
- Java SDK 11
- Maven 3
- Spotify API Credentials (with ugc-image-upload & playlist-modify-*) permissions

## How to use
- Clone repository to "C:\\"
- Rename config.properties.example to config.properties
- Add your Spotify API credentials to config.properties
- Define your playlists in MyPlaylists class

## Running the code in the background on your Windows machine
Open Task Scheduler and create a new task to run a program with admin permissions, schedule it to 5 minutes interval after first run.
```
Program to start: C:\Windows\System32\wscript.exe
Argument: "C:\spotify-playlist-updater\runner.vbs"
Start in: C:\spotify-playlist-updater
```
