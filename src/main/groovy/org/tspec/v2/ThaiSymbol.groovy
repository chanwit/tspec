package org.tspec.v2

import groovy.lang.Closure

class ThaiSymbol {

    String value
    Closure action

    String toString() { "$value: $action" }
}
