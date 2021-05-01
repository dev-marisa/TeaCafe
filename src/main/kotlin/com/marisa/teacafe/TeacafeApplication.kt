package com.marisa.teacafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.*

@Table("TEAS")
data class Tea(@Id val id: String?, val flavor: String)

interface TeaRepository : CrudRepository<Tea, String>

@RestController
class TeaController(val teaCart: TeaRepository) {

    @GetMapping("/menu")
    fun menu(): List<Tea> = teaCart.findAll() as List<Tea>

    @PostMapping("/tea")
    fun add(@RequestBody newTea: Tea): String {
        teaCart.save(newTea)
        return "ok"
    }

    @DeleteMapping("/tea/{id}")
    fun remove(@PathVariable("id") id: String): String {
        teaCart.deleteById(id)
        return "ok"
    }

    @PutMapping("tea/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody theTea: Tea): String {
        teaCart.save(theTea.copy(id=id))
        return "ok"
    }

}

@SpringBootApplication
class TeacafeApplication

fun main(args: Array<String>) {
    runApplication<TeacafeApplication>(*args)
}