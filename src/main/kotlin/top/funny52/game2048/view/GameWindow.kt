package top.funny52.game2048.view



import top.funny52.game2048.controller.getHistory
import top.funny52.game2048.function.resetGame
import javax.swing.*
import top.funny52.game2048.dao.DataBase


class GameWindow(title: String): JFrame(title) {
    private val menuBar = JMenuBar()
    private val gameMenu = JMenu("游戏")
    private val menuInfo = JMenuItem("个人信息")
    private val menuHistory = JMenuItem("历史记录")
    init {
        //设置程序的menubar显示到Mac的菜单栏上
        System.setProperty("apple.laf.useScreenMenuBar", "true")
        //设定Mac中程序运行时菜单栏显示的程序名称，此项并非一定能成功
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", title)
    }
    init {
        this.gameMenu.let {
            it.add(menuInfo)
            it.add(menuHistory)
        }
        this.menuBar.add(this.gameMenu)
        this.jMenuBar = this.menuBar

        this.menuInfo.apply {
            this.addActionListener {
                Game.Name = JOptionPane.showInputDialog("请输入挑战者名字:")

            }
        }
        this.menuHistory.apply {
            this.addActionListener {
                DialogList(
                    this@GameWindow,
                    "历史记录",
                    DataBase.getHistory(10)
                        .map { "${it.name}    ${it.grade}" }.toTypedArray()
                )
            }
        }
        this.add(Game().apply { this.resetGame() })
        this.setSize(513, 708)
        this.isVisible = true
        this.setLocationRelativeTo(null)
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }
}