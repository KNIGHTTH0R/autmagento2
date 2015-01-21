Global $Paused, $Runner
HotKeySet("{PAUSE}", "TogglePause")
HotKeySet("{ESC}", "Terminate")
HotKeySet("{F9}", "ShowMe")


While 1

    Sleep(100)
WEnd
;;;;;;;;

Func TogglePause()
    $Paused = Not $Paused
    While $Paused
        Sleep(100)
        ToolTip('Script is "Paused"', 0, 0)
    WEnd
    ToolTip("")
EndFunc   ;==>TogglePause

Func Terminate()
    Exit 0
EndFunc   ;==>Terminate

Func ShowMe()
    $Runner = Not $Runner
    While $Runner
		$checkWin = WinExists("Authentication Required")

if $checkWin = 1 Then
	WinActivate($checkWin)
	Sleep(500)
	;ControlClick("")
	MouseClick("primary", 261, 230, 1)
	Sleep(500)
	Send("pippajean")
	Sleep(500)
	Send("{TAB}")
	Sleep(500)
	Send("sn0wMob1l!")
	Sleep(500)
	Send("{ENTER}")

	;MsgBox(64, "Found It", "yes")
	;ControlClick()

	EndIf

    WEnd
EndFunc   ;==>ShowMe


;Local $checkWin = WinWait("Authentication Required", "", 100)



