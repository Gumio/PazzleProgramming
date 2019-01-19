package com.kcg.project.pazpro.service

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileNotFoundException
import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.LogStream


@Service
class DockerService {
    private val PATH = "./files/main.rb"
    private val CONTAINER_ID = "pazpro_ruby"
    private val COMMAND = arrayOf("ruby", "/tmp/files/main.rb")

    fun createMainFile(code: String) {
        try {
            val src = File(PATH)
            src.writeText(code)
        } catch (e: FileNotFoundException) {
            println(e)
        }
    }

//    fun runCode(): String {
//        val dockerClient = DockerClientBuilder.getInstance("tcp://localhost:2376").build()
//        return dockerClient
//            .execCreateCmd(CONTAINER_ID)
//            .withCmd(*COMMAND)
//            .withAttachStdout(true)
//            .withAttachStderr(true)
//            .exec().toString()
//    }

    fun runCode(args: String): String {
        val docker = DefaultDockerClient.fromEnv().build()
        val execCreation = docker.execCreate(CONTAINER_ID, arrayOf(COMMAND[0], COMMAND[1], args),
            DockerClient.ExecCreateParam.attachStdout(),
            DockerClient.ExecCreateParam.attachStderr())
        val out: LogStream = docker.execStart(execCreation.id())
        return out.readFully()
    }
}