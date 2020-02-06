package com.imooc.test;

import com.imooc.model.PlayList;
import com.imooc.model.PlayListCollection;
import com.imooc.model.Song;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TestDemo {

    private static PlayListCollection playListCollection;

    public static void main(String[] args) {
        test();

        try {
            FileInputStream fis = new FileInputStream("111");
            byte[] b = new byte[10];
            fis.read(b,);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //测试歌曲类
    public static void testSong() {

    }

    //测试播放列表类
    public static void testPlayList() {

    }

    //测试播放器类
    public static void testPlayListConnect() {

    }

    //主菜单
    public static void mainMenu() {
        System.out.println("******************************");
        System.out.println("          **主菜单**           ");
        System.out.println("          1--播放列表管理");
        System.out.println("          2--播放器管理");
        System.out.println("          0--退出");
        System.out.println("******************************");
        System.out.println("请输入对应数字进行操作：");
    }

    //播放列表管理菜单
    public static void playListMenu() {
        System.out.println("播放列表管理主要功能：");
        System.out.println("1、将歌曲添加到主播放列表");
        System.out.println("2、将歌曲添加到普通播放列表");
        System.out.println("3、通过歌曲id查询播放列表中的歌曲");
        System.out.println("4、通过歌曲名称查询播放列表中的歌曲");
        System.out.println("5、修改播放列表中的歌曲");
        System.out.println("6、删除播放列表中的歌曲");
        System.out.println("7、显示播放列表中的所有歌曲");
        System.out.println("8、导出歌单");
        System.out.println("9、返回上一级菜单");
    }

    /**
     * 播放列表操作器
     */
    public static void playListMenuOperator(int tag) {
        Scanner scanner = new Scanner(System.in);
        boolean inputMismatch = true;
        //1、将歌曲添加到主播放列表
        if (tag == 1) {
            int songNum = -1;
            System.out.println("将歌曲添加到主播放列表");
            while (inputMismatch) {
                try {
                    songNum = scanner.nextInt();
                    if (songNum > 0) {
                        inputMismatch = false;
                    } else {
                        inputMismatch = true;
                        System.out.println("请输入要添加的歌曲数量：");
                    }
                } catch (InputMismatchException e) {
                    String str = scanner.next();
                    inputMismatch = true;
                    System.out.println("请输入要添加的歌曲数量：");
                }
            }
            for (int i = 1; i <= songNum; i++) {
                Song song = new Song();
                String str = "";
                System.out.println("请输入第" + i + "首歌曲：");
                System.out.println("请输入歌曲的id：");
                str = scanner.nextLine();
                song.setId(str);
                System.out.println("请输入歌曲的名称：");
                str = scanner.nextLine();
                song.setName(str);
                System.out.println("请输入演唱者：");
                str = scanner.nextLine();
                song.setSinger(str);
            }
        }
        //2、将歌曲添加到普通播放列表
        if (tag == 2) {

        }
        //3、通过歌曲id查询播放列表中的歌曲
        if (tag == 3) {

        }
        //4、通过歌曲名称查询播放列表中的歌曲
        if (tag == 4) {

        }
        //5、修改播放列表中的歌曲
        if (tag == 5) {

        }
        //6、删除播放列表中的歌曲
        if (tag == 6) {

        }
        //7、显示播放列表中的所有歌曲
        if (tag == 7) {

        }
        //8、导出歌单
        if (tag == 8) {

        }
    }

    //播放器菜单
    public static void playerMenu() {
        System.out.println("播放器管理主要功能：");
        System.out.println("1、向播放器添加播放列表");
        System.out.println("2、从播放器删除播放列表");
        System.out.println("3、通过名字查询播放列表信息");
        System.out.println("4、显示所有播放列表名称");
        System.out.println("9、返回上一级菜单");
    }

    //播放器菜单操作器
    public static void playerMenuOperator(int tag) {
        Scanner scanner = new Scanner(System.in);
        boolean inputMismatch = true;
        //向播放器添加播放列表
        if (tag == 1) {

        }
        //从播放器删除播放列表
        if (tag == 2) {

        }
        //通过名字查询播放列表信息
        if (tag == 3) {

        }
        //显示所有播放列表名称
        if (tag == 4) {

        }
    }

    //创建播放列表管理对象以及默认播放列表
    public static void initPlayer() {
        //初始化播放列表管理对象
        playListCollection = new PlayListCollection();

        //初始化播放器列表
        String mainListName = "主播放列表";
        List<Song> mainMusicList = new ArrayList<Song>();
        PlayList mainList = new PlayList(mainListName, mainMusicList);

        //添加到播放器列表管理对象中
        playListCollection.addPlayList(mainList);
    }

    //主流程实现
    public static void test() {
        //创建播放列表管理对象以及默认播放列表
        initPlayer();

        int menuLocation = 1;
        int mainMenuTag = -1;
        int playListMenuTag = -1;
        int playerMenuTag = -1;
        boolean inputMismatch = true;
        Scanner scanner = new Scanner(System.in);
        mainMenu();

        while (true) {
            while (inputMismatch && menuLocation == 1) {
                try {
                    mainMenuTag = scanner.nextInt();
                    if (mainMenuTag >= 0 && mainMenuTag <= 2) {
                        menuLocation = 2;
                        inputMismatch = false;
                    } else {
                        inputMismatch = true;
                        System.out.println("请输入对应数字进行操作：");
                    }
                } catch (InputMismatchException e) {
                    inputMismatch = true;
                    String str = scanner.next();
                    System.out.println("请输入对应数字进行操作：");
                }
            }
            if (mainMenuTag == 0) {
                break;
            } else if (mainMenuTag == 1) {
                playListMenu();
                inputMismatch = true;
                while (true) {
                    while (inputMismatch && menuLocation == 2) {
                        try {
                            playListMenuTag = scanner.nextInt();
                            if (playListMenuTag >= 1 && playListMenuTag <= 9) {
                                inputMismatch = false;
                            } else {
                                inputMismatch = true;
                                System.out.println("请输入对应数字进行操作：");
                            }
                        } catch (InputMismatchException e) {
                            inputMismatch = true;
                            String str = scanner.next();
                            System.out.println("请输入对应数字进行操作：");
                        }
                    }
                    if (playListMenuTag == 9) {
                        inputMismatch = true;
                        break;
                    } else {
                        playListMenuOperator(playListMenuTag);
                    }
                }
            } else if (mainMenuTag == 2) {
                playerMenu();
                inputMismatch = true;
                while (true) {
                    while (inputMismatch && menuLocation == 2) {
                        try {
                            playerMenuTag = scanner.nextInt();
                            if (playerMenuTag >= 1 && playerMenuTag <= 4 || playerMenuTag == 9) {
                                inputMismatch = false;
                            } else {
                                inputMismatch = true;
                                System.out.println("请输入对应数字进行操作：");
                            }
                        } catch (InputMismatchException e) {
                            inputMismatch = true;
                            String str = scanner.next();
                            System.out.println("请输入对应数字进行操作：");
                        }
                    }
                    if (playerMenuTag == 9) {
                        inputMismatch = true;
                        break;
                    } else {
                        playerMenuOperator(playerMenuTag);
                    }
                }
            }
        }
    }
}
