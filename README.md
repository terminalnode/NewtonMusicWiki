# Newton Music Wiki
Simple music wiki for a web development project at Newton. The wiki will only support basic
CRUD operations and possibly authentication via JWT tokens.

## Local setup
Set your `spring.profiles.active` environment variable to `local` to run against a local database.

Set up the `music_wiki` user and `music_wiki` database by running the following commands:
`CREATE USER music_wiki WITH CREATEDB PASSWORD 'newton';`
`CREATE DATABASE music_wiki WITH OWNER music_wiki ENCODING 'UTF8';`

Then run `MusicWikiApplication.kt`.