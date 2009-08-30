package org.tspec.hangman

import org.tspec.hangman.Hangman

เรื่อง "คนแขวนคอ"

อธิบาย "การตั้งค่าที่เหมาะสมสำหรับเริ่มต้น" {
    กำหนดให้ 'มีวัตถุคนแขวนคอ', {
        hangman = new Hangman()
    }
    เมื่อ 'ตั้งค่าคำไว้เป็นค่า hello',{
        hangman.word = 'hello'
    }
    แล้ว 'ตัววัตถุควรมีการตั้งค่าที่เหมาะสม',{
        hangman.word.should == 'hello'
        hangman.wrongs.should == 0
        hangman.maxGuess.should == 12
        hangman.unrevealedWord.should == ['_', '_', '_', '_', '_']
        hangman.finished.should == false
    }
}

อธิบาย 'การตั้งค่าจำนวนครั้งที่เล่นผิดให้เป็น 0 ถ้าผู้เล่นต้องการเล่นเกมใหม่',{
    กำหนดให้ 'มีวัตถุคนแขวนคอ เพื่อเล่นคำว่า hello', {
        hangman = new Hangman()
        hangman.word = 'hello'
    }
    เมื่อ 'ผู้เล่นเล่นเกมไปแล้วด้วยการทาย a และสั่งให้เริ่มเกมใหม่', {
        hangman.guess('a')
        hangman.newGame()
    }
    แล้ว 'จำนวนครั้งของการเล่นผิดควรเป็น 0',{
        hangman.wrongs.should == 0
    }
}

อธิบาย 'การเดาผิดหมด', {
    กำหนดให้ 'มีวัตถุคนแขวนคอ เพื่อเล่นคำว่า hello', {
        hangman = new Hangman()
        hangman.word = 'hello'
    }
    เมื่อ 'เดาครั้งแรกผิด', {
        hangman.guess('a')
    }
    แล้ว 'จำนวนการผิดควรเป็น 1', {
        hangman.wrongs.should == 1
    }
    และ 'เมื่อเดาครั้งที่ 2 ผิด จำนวนการผิดควรเป็น 2',{
        hangman.guess('b')
        hangman.wrongs.should == 2
    }
    และ 'เมื่อเดาผิดครบ 12 ครั้ง จำนวนการผิดควรเป็น 12', {
        for(i in 3..12) {
            hangman.guess('p')
            hangman.wrongs.should == i
        }
    }
    แล้ว 'หากเดาผิดอีกครั้ง ควรขว้างข้อผิดพลาดจำนวนครั้งที่เดาเกิน', {
        รับรองว่าขว้าง ExceedGuessException, {
            hangman.guess('p')
        }
    }
}

อธิบาย 'การเดาถูกหมด', {
    กำหนดให้ 'มีวัตถุคนแขวนคอ เพื่อเล่นคำว่า hello', {
        hangman = new Hangman()
        hangman.word = 'hello'
    }
    เมื่อ 'เดาครั้งแรกถูก', {
        hangman.guess('h')
    }
    แล้ว 'จำนวนครั้งที่ผิดควรเป็น 0',{
        hangman.wrongs.should == 0
    }
    และ 'คำที่ซ่อนอยู่ควรเป็น h และช่องว่าง 4 ตัว', {
        hangman.unrevealedWord.should == ['h','_','_','_','_']
    }
    และเมื่อ 'ทายด้วย e', {
        hangman.guess('e')
    }
    แล้ว 'จำนวนครั้งที่ผิดควรจะยังเป็น 0 อยู่', {
        hangman.wrongs.should == 0
    }
    และ 'คำที่ซ่อนอยู่ควรเป็น he และช่องว่าง 3 ตัว', {
        hangman.unrevealedWord.should == ['h','e','_','_','_']
    }
    และเมื่อ 'ทายด้วย l', {
        hangman.guess('l')
    }
    แล้ว 'จำนวนครั้งที่ผิดควรจะยังเป็น 0 อยู่', {
        hangman.wrongs.should == 0
    }
    และ 'คำที่ซ่อนอยู่ควรเป็น hell และช่องว่าง 1 ตัว', {
        hangman.unrevealedWord.should == ['h','e','l','l','_']
    }
    และเมื่อ 'ทายด้วย o', {
        hangman.guess('o')
    }
    แล้ว 'จำนวนครั้งที่ผิดควรจะยังเป็น 0 อยู่', {
        hangman.wrongs.should == 0
    }
    และ 'คำที่ซ่อนอยู่ควรเป็น hello และจบการเล่น', {
        hangman.unrevealedWord.should == ['h','e','l','l','o']
        hangman.finished.should == true
    }
}

