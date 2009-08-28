package org.tspec.report.dom

import groovy.xml.*

class Report{

    Date date
    List<Story> stories = []

    def toXML() {
        def writer = new StringWriter()
        def builder = new MarkupBuilder(writer)
        builder.report() {
            date(this.date)
            stories.each {
            	story {
            		
            	}
            }
        }
    }

}
