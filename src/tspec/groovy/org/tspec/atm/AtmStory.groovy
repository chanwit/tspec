package org.tspec.atm;

เรื่อง ระบบเอทีเอ็ม

ก่อน ทุกสถานการณ์ตั้งค่าวัตถุ {
    acc     = new Account()
    machine = new CashMachine()
}

สถานการณ์ บัญชีมีเครดิต {
    กำหนดให้ บัญชีมีเครดิต	{ acc.credit = 1000 }
    และ บัตรยังใช้ได้		{ acc.card = true   }
    และ ตู้เอทีเอ็มมีเงินพอ	{ atm.cash = 100    }
    เมื่อ ลูกค้าเรียกเบิกเงิน	{
    atm.cardIn = true
    atm.withdraw
    }
    และ บัญชีถูกเดบิต		{ acc.withdraw(100) }
    แล้ว ตู้คืนบัตร		{ atm.cardIn.should == false }
}

สถานการณ์ บัญชีติดลบ {

}
