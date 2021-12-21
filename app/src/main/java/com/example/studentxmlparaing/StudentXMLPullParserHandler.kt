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

            while (eventType != XmlPullParser.END_DOCUMENT) { //To take the content of XML document (before the end of the document)
                val tagName = parser.name

                when(eventType) {
                    // "text = " is the var that is initialized above
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("name", ignoreCase = true) -> {
                            studentName = text!!.toString()
                        } // "name", "grade" are the names of the tags we want to get
                        tagName.equals("grade", ignoreCase = true) -> {
                            studentGrade = text!!.toFloat()
                            students.add(Student(studentName,studentGrade))
                        }
                        //else -> students.add(Student(studentName,studentGrade)) //This line will add even the default value we give to "studentName", "studentGrade"
                    }
                    else -> {  // NOTE: didn't understand why to do this
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