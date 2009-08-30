package org.tspec.stack;

พฤติกรรม "กองซ้อน"

ก่อน "รัน spec กำหนดให้มีกองซ้อน" {
	stack = new Stack()
}

วัตถุ "ควรมีขนาดเป็น 1 เมื่อทำการ push 1 ครั้ง" {
	stack.push(10)
	stack.size().should == 1
}