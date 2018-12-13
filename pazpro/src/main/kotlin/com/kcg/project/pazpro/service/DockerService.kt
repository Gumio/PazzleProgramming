package com.kcg.project.pazpro.service

//import com.spotify.docker.client.DefaultDockerClient
//import com.spotify.docker.client.DockerClient
//import com.spotify.docker.client.LogStream
//import org.springframework.stereotype.Service
//import java.io.File
//import java.io.FileNotFoundException
//
//@Service
//class DockerService {
//    private val PATH = "./files/main.rb"
//    private val CONTAINER_ID = ""
//    private val COMMAND = arrayOf("ruby", "/tmp/files/main.rb")
//
//    fun createMainFile(code: String) {
//        try {
//            val src = File(PATH)
//            src.writeText(code)
//        } catch (e: FileNotFoundException) {
//            println(e)
//        }
//    }
//
//fun runCode(): String {
//    val docker = DefaultDockerClient.fromEnv().build()
//    docker.startContainer(CONTAINER_ID)
//    val execCreation = docker.execCreate(CONTAINER_ID, COMMAND,
//        DockerClient.ExecCreateParam.attachStdout(),
//        DockerClient.ExecCreateParam.attachStderr())
//    val out: LogStream = docker.execStart(execCreation.id())
//    docker.killContainer(CONTAINER_ID)
//    return out.readFully()
//}
//}