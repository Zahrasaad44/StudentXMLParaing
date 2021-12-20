package com.example.studentxmlparaing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentxmlparaing.databinding.StudentRowBinding

class StudentsAdapter(var students: List<Student>): RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {
    class StudentsViewHolder(val binding: StudentRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        return StudentsViewHolder(StudentRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
       val student = students[position]

        holder.binding.apply {
            nameTV.text = student.name
            gradesTV.text = student.grade.toString()
        }
    }

    override fun getItemCount(): Int {
       return students.size
    }
    fun updateDetails(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }
}