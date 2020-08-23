package signature

import java.io.File
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val fsName = scanner.nextLine().split(" ")
    //val fsName = "A b".split(" ")
    //val fsName = "Tom Smith".split(" ")
    //val fsName = "Ian One".split(" ")
    val fName = fsName[0]
    val sName = fsName[1]
    print("Enter person's status: ")
    //val status = "LONG PARTICIPANT"//scanner.nextLine()
    //val status = "Worker"//scanner.nextLine()
    //val status = "VIP"//scanner.nextLine()
    val status = scanner.nextLine()
    val fileName = "/Users/ilyakress/Documents/JBA/ASCII Text Signature/ASCII Text Signature (1) (1) (1) (1)/task/src/roman.txt"
    var filelines = File(fileName).readLines()

    var len = 4 + 2 + 10 + 2
    val space = "          "
    val lenName = nameLen(fName, space, sName, filelines) + len
    var lenM = 4
    var wM = 0
    val fileNameM = "/Users/ilyakress/Documents/JBA/ASCII Text Signature/ASCII Text Signature (1) (1) (1) (1)/task/src/medium.txt"
    val filelinesM = File(fileNameM).readLines()
    val lenStatus = statusLen(status, filelinesM)
    var w = 0
    val lines = Array<String>(10, { _ -> "" })

    for (i in 0..9) {
        lines[i] = lines[i] + "88  "
    }

    for (c in fName) {
        var lstart = 0;
        var lend = 0;
        for (i in 0..filelines.size - 1) {
            if (filelines[i].split(" ").size == 2) {
                if (filelines[i][0] == c && filelines[i].split(" ")[0].length == 1) {
                    lstart = i + 1
                    lend = lstart + 10
                    w = filelines[i].split(" ")[1].toInt()
                    len = len + w
                    var lnum = 0
                    for (ind in lstart..lend - 1) {
                        lines[lnum] = lines[lnum] + filelines[ind]
                        lnum++
                    }
                    break
                }
            }
        }
    }

    for (i in 0..9) {
        lines[i] = lines[i] + space
    }
    for (c in sName) {
        var lstart = 0;
        var lend = 0;
        for (i in 0..filelines.size - 1) {
            if (filelines[i].split(" ").size == 2) {
                if (filelines[i][0] == c && filelines[i].split(" ")[0].length == 1) {
                    lstart = i + 1
                    lend = lstart + 10
                    w = filelines[i].split(" ")[1].toInt()
                    len = len + w
                    var lnum = 0
                    for (ind in lstart..lend - 1) {
                        lines[lnum] = lines[lnum] + filelines[ind]
                        lnum++
                    }
                    break
                }
            }
        }
    }


    val linesM = Array<String>(3, { _ -> "" })

    if (lenName >= lenStatus) {
        for (i in 0..2) {
            linesM[i] = linesM[i] + "88"
            if (lenStatus % 2 != 0 && lenName % 2 == 0) {
                linesM[i] = linesM[i].padEnd((-1 + (lenName - 10) / 2) - lenStatus / 2)
            } else {
                linesM[i] = linesM[i].padEnd((0 + (lenName - 10) / 2) - lenStatus / 2)
            }
        }
    } else {
        for (i in 0..2) {
            linesM[i] = linesM[i] + "88  "
        }
    }

    for (c in status) {
        if (c != ' ') {
            var lstart = 0;
            var lend = 0;
            for (i in 0..filelinesM.size - 1) {
                if (filelinesM[i].split(" ").size == 2) {
                    if (filelinesM[i][0] == c) {
                        lstart = i + 1
                        lend = lstart + 3
                        wM = filelinesM[i].split(" ")[1].toInt()
                        lenM = lenM + wM
                        var lnum = 0
                        for (ind in lstart..lend - 1) {
                            linesM[lnum] = linesM[lnum] + filelinesM[ind]
                            lnum++
                        }
                        break
                    }
                }
            }
        } else {
            for (i in 0..linesM.size - 1) {
                linesM[i] = linesM[i] + "".padStart(5, ' ')
            }
        }
    }

    var border = ""
    for (i in 0..len - 1) {
        border += "8"
    }
    for (i in 0..9) {
        lines[i] = lines[i] + "  88"
    }
    if (lenName < lenStatus) {
        for (i in 0..9) {
            var start = false
            while (lines[i].length < lenStatus + 8) {
                if (start) {
                    lines[i] = "88 " + lines[i].removePrefix("88")
                    start = false;
                } else {
                    lines[i] = lines[i].removeSuffix("88") + " 88"
                    start = true
                }
            }
        }
        border = "".padStart(lenStatus + 8, '8')
    }
    println("")
    println(border)
    for (l in lines) {
        println(l)
    }
    for (l in linesM) {
        var lp = l;
        while (lp.length < len - 4) {
            lp = lp + " "
        }
        println(lp + "  88")
    }
    println(border)
    //}
}

fun nameLen(nameF: String, space: String, nameL: String, filelines: List<String>): Int {
    var len = 0;
    return strLen(nameF, filelines) + space.length + strLen(nameL, filelines)
}

fun strLen(str: String, filelines: List<String>): Int {
    var len = 0
    for (c in str) {
        for (i in 0..filelines.size - 1) {
            if (filelines[i].split(" ").size == 2) {
                if (filelines[i][0] == c && filelines[i].split(" ")[0].length == 1) {
                    val w = filelines[i].split(" ")[1].toInt()
                    len = len + w
                    break
                }
            }
        }
    }
    return len
}

fun statusLen(status: String, filelinesM: List<String>): Int {
    var lenM = 0;
    for (c in status) {
        if (c != ' ') {
            var lstart = 0;
            var lend = 0;
            for (i in 0..filelinesM.size - 1) {
                if (filelinesM[i].split(" ").size == 2) {
                    if (filelinesM[i][0] == c) {
                        lstart = i + 1
                        lend = lstart + 3
                        val wM = filelinesM[i].split(" ")[1].toInt()
                        lenM = lenM + wM
                        break
                    }
                }
            }
        } else {
            lenM += 5
        }
    }
    return lenM
}

