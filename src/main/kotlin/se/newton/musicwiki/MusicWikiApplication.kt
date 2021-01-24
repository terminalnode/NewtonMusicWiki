package se.newton.musicwiki

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicWikiApplication

fun main(args: Array<String>) {
  runApplication<MusicWikiApplication>(*args)
}