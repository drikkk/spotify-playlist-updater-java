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
- Add your Spotify API credentials to config.properties
- Define your playlists in MyPlaylists.class 
- Execute main class through IDE or with Maven: 
- ```mvn -f C:\path\to\project\root\pom.xml exec:java -Dexec.mainClass="fix.my.playlist.Main" --quiet```

## Running the script in the background on your Windows machine
- Create a .bat file: 
```
CALL mvn -f C:\path\to\project\root\pom.xml exec:java -Dexec.mainClass="fix.my.playlist.Main" --quiet
```
- Create a .svb file: 
```
Dim WinScriptHost
Set WinScriptHost = CreateObject("WScript.Shell")
WinScriptHost.Run Chr(34) & "C:\path\to\your\bat\file.bat" & Chr(34), 0
Set WinScriptHost = Nothing
```
- Open Windows Task Scheduler and create a task that executes (It might require Administrators group permission)
```
C:\Windows\System32\wscript.exe "C:\path\to\your\svb\file.svb"
```

Now your Spotify playlist is protected from spam bots and will be updated as per your scheduler configuration. Enjoy!
