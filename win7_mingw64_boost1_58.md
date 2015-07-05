win7 mingw64 boost1.58 配置编译

下载并安装mingw64, http://sourceforge.net/projects/mingw-w64/

下载并安装msys64, http://sourceforge.net/projects/msys2

下载并解压boost1.58, http://www.boost.org/


楼主的安装路径：
 mingw64 =》 c:\mingw64
 msys64 =》 c:\mingw64\msys64
 boost1.58 =》 c:\mingw64\inlucde\boost

配置环境变量：
C_INCLUDEDE_PATH     || C:\mingw64\include;C:\mingw64\x86_64-w64-mingw32\include
CPLUS_INCLUDE_PATH || C:\mingw64\include;C:\mingw64\x86_64-w64-mingw32\include;C:\mingw64\x86_64-w64-mingw32\include\c++;
Path                                  || 追加C:\mingw64\bin;

配置好上面，可以试一下是否能用g++来编译普通的c++程序，这里就不详细介绍

现在开始boost编译流程，这里需要 msys64环境，安装，然后进去64shell

c:\mingw64\inlucde\boost
 
再执行

./bootstrap.sh --with-toolset=mingw  

再执行

./b2 variant=release link=shared threading=multi runtime-link=shared --with-thread --with-system --with-signals --with-regex

最后就是等待编译了，最后输出内容为：
The Boost C++ Libraries were successfully built!

The following directory should be added to compiler include paths:

    C:/mingw64/include/boost

The following directory should be added to linker library paths:

    C:\mingw64\include\boost\stage\lib

也就是说头文件在 C:/mingw64/include/boost
编译出来的动态库文件在 C:\mingw64\include\boost\stage\lib

注：--with-xxx这里楼主是为随便编译几个来试试，可以按实际需要加入相关的库

然后把ddl结尾在放到C:\mingw64\bin, 因为我们的path配置在这里，放到这里方便编译动态库和执行时找到相关的文件

PS：b2和bjam是一样的东西，这里列了一下相关的参数：
 --build-dir=<builddir>          编译的临时文件会放在builddir里(这样比较好管理，编译完就可以把它删除了) 
--stagedir=<stagedir>          存放编译后库文件的路径，默认是stage 
--build-type=complete          编译所有版本，不然只会编译一小部分版本（确切地说是相当于:variant=release, threading=multi;link=shared|static;runtime-link=shared） 
variant=debug|release         决定编译什么版本(Debug or Release?) 
link=static|shared                 决定使用静态库还是动态库。 
threading=single|multi          决定使用单线程还是多线程库。 
runtime-link=static|shared    决定是静态还是动态链接C/C++标准库。 
--with-<library>                     只编译指定的库，如输入--with-regex就只编译regex库了。 
--show-libraries                    显示需要编译的库名称

参考：
http://www.boost.org/doc/libs/1_58_0/more/getting_started/unix-variants.html
http://blog.csdn.net/asb2010/article/details/43055391
http://blog.csdn.net/yasi_xi/article/details/8660549 ;
http://www.360doc.com/content/11/0128/15/59141_89586432.shtml ;
