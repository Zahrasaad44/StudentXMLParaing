package com.example.studentxmlparaing

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.util.ArrayList

class StudentXMLPullParserHandler {
    private var students = ArrayList<Student>()
    private var text: String? = null

    private var studentName = ""
    private var studentGrade = 0f

    fun parseStudentXML(inputStream: InputStream): List<Student> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name

                when(eventType) {
                    // "text = " is the var that is initialized above
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("name", ignoreCase = true) -> {
                            studentName = text!!.toString()
                        }
                        tagName.equals("grade", ignoreCase = true) -> {
                            studentGrade = text!!.toFloat()
                        }
                        else -> students.add(Student(studentName,studentGrade))
                    } // SOMETHING I DIDN'T UNDERSTAND: when I comment these two "else"s, I get nothing in the RV
                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (exception: XmlPullParserException) {
            exception.printStackTrace()
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
        return students
    }

}