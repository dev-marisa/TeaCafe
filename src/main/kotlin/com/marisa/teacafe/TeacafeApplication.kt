package com.marisa.teacafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Table("TEAS")
data class Tea(@Id val id: String?, val flavor: String)

interface TeaRepository : CrudRepository<Tea, String>{}

@RestController
class TeaController(val teaCart: TeaRepository) {

    @GetMapping("/menu")
    fun menu(): List<Tea> = teaCart.findAll() as List<Tea>

    @PostMapping("/tea")
    fun add(@RequestBody newTea: Tea): String {
        teaCart.save(newTea)
        return "ok"
    }

}

@SpringBootApplication
class TeacafeApplication

fun main(args: Array<String>) {
    runApplication<TeacafeApplication>(*args)
}