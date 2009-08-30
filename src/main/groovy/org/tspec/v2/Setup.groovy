package org.tspec.v2

import groovy.lang.MissingMethodException

import org.tspec.v2.behaviour.*;
import org.tspec.v2.story.*;

class Setup {

    static trace = true
    static initialised = false

    static setupShould() {
        Object.metaClass.getShould = { ->
            new ShouldObject(delegate)
        }
    }

    static setupMissing() {
        Script.metaClass.propertyMissing = { String name ->
            if(trace) println "trace >> propertyMissing $name"
            if(name.charAt(0) >= 'ก'.charAt(0))
                return new ThaiSymbol(value:name)
            else
                throw new MissingPropertyException(name, delegate.class)
        }

        Script.metaClass.methodMissing = { String name, args ->
            if(trace) println "trace >> methodMissing $name: ${args.collect{it}.join(',')}"
            if(name.charAt(0) >= 'ก'.charAt(0) && args[0] instanceof Closure)
                return new ThaiSymbol(value: name, action: args[0])
            else
                throw new MissingMethodException(name, delegate.class, args)
        }
    }

    static setupAdvice() {
        /**
         * Before/After
         */
        Script.metaClass."ก่อน" = { ThaiSymbol beforeBody ->
            println "ก่อน" + beforeBody.value + beforeBody.action
        }
        Script.metaClass."หลัง" = { ThaiSymbol beforeBody ->
            println "หลัง" + beforeBody.value + beforeBody.action
        }
    }

    static setupStory() {
        /**
         * Story
         */
        Script.metaClass."เรื่อง" = { ThaiSymbol s ->
            def binding = delegate.binding
            if(binding.variables.containsKey("root"))
                throw new RuntimeException("Root node already defined");

            binding.root = new Story(title: s.value)
            if(trace) println "เรื่อง" + s.value
        }
        Script.metaClass."อธิบาย" = { ThaiSymbol s ->
            if(trace) println "อธิบาย" + s.value
        }
        Script.metaClass."สถานการณ์" = { ThaiSymbol s ->
            def binding = delegate.binding
            if(!(binding.variables.containsKey("root")))
                throw new RuntimeException("Story node is not defined");
            def root = binding.root
            if(!(root instanceof Story))
                throw new RuntimeException("Story node is required found [${root.class}]");

            root.scenario << new Scenario(title: s.value, action: s.action)
            if(trace) println "สถานการณ์" + s.value
        }
    }

    static setupBehaviour() {
        /**
         * Behaviour
         */
        Script.metaClass."พฤติกรรม" = { ThaiSymbol s ->
            def binding = delegate.binding
            if(binding.variables.containsKey("root"))
                throw new RuntimeException("Root node already defined");

            binding['root'] = new Behaviour(title: s.value)
            if(trace) println "พฤติกรรม[ของ]" + s.value
        }
        Script.metaClass."พฤติกรรมของ" = { ThaiSymbol s ->
            def binding = delegate.binding
            if(binding.variables.containsKey("root"))
                throw new RuntimeException("Root node already defined");

            binding['root'] = new Behaviour(title: s.value)
            if(trace) println "พฤติกรรมของ" + s.value
        }
        Script.metaClass."วัตถุ" = { ThaiSymbol s ->
            def binding = delegate.binding
            if(!(binding.variables.containsKey("root")))
                throw new RuntimeException("Behaviour node is not defined");
            def root = binding.root
            if(!(root instanceof Behaviour))
                throw new RuntimeException("Behaviour node is required found [${root.class}]");

            root.cases << new Case(title: s.value, action: s.action)
            if(trace) println "วัตถุ" + s.value
        }
    }


    static setup() {
        if(initialised) return

        setupMissing()
        setupShould()
        setupAdvice()
        setupStory()
        setupBehaviour()

        initialised = true
    }

}