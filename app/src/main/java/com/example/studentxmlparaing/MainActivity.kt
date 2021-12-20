package com.example.studentxmlparaing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentxmlparaing.databinding.ActivityMainBinding
import com.example.studentxmlparaing.databinding.StudentRowBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: StudentsAdapter
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        students = listOf()
        recyclerAdapter = StudentsAdapter(students)
        binding.studentRV.adapter = recyclerAdapter
        binding.studentRV.layoutManager = LinearLayoutManager(this)

        try {
            val parser = StudentXMLPullParserHandler()
            val studentSteam = assets.open("students.xml")
            students = parser.parseStudentXML(studentSteam)
            recyclerAdapter.updateDetails(students)

        } catch (e: IOException) {
            println("ISSUE: $e")
        }

    }
}