win7 mingw64 boost1.58 ���ñ���
===

###���׼����
    ���ز���װmingw64, http://sourceforge.net/projects/mingw-w64/
    ���ز���װmsys64, http://sourceforge.net/projects/msys2
    ���ز���ѹboost1.58, http://www.boost.org/
  


######¥���İ�װ·����
    mingw64 =�� c:\mingw64
    msys64 =�� c:\mingw64\msys64
    boost1.58 =�� c:\mingw64\inlucde\boost

###���û���������
    C_INCLUDEDE_PATH     || C:\mingw64\include;C:\mingw64\x86_64-w64-mingw32\include;

    CPLUS_INCLUDE_PATH   || C:\mingw64\include;C:\mingw64\x86_64-w64-mingw32\include;C:\mingw64\x86_64-w64-mingw32\include\c++;

    Path                 || ׷��C:\mingw64\bin;

<br>


    ���ú����棬������һ���Ƿ�����g++��������ͨ��c++��������Ͳ���ϸ����

###���ڿ�ʼboost�������̣�������Ҫ msys64��������װ��Ȼ���ȥ64shell<br>  

cd c:\mingw64\inlucde\boost

    ��ִ��

./bootstrap.sh --with-toolset=mingw  

    ��ִ��

./b2 variant=release link=shared threading=multi runtime-link=shared --with-thread --with-system --with-signals --with-regex


    �����ǵȴ������ˣ�����������Ϊ:

    The Boost C++ Libraries were successfully built!

    The following directory should be added to compiler include paths:

        C:/mingw64/include/boost

    The following directory should be added to linker library paths:

        C:\mingw64\include\boost\stage\lib
    


    Ҳ����˵ͷ�ļ��� C:/mingw64/include/boost

    ��������Ķ�̬���ļ��� C:\mingw64\include\boost\stage\lib


    ע��--with-xxx����¥����Ϊ�����뼸�������ԣ����԰�ʵ����Ҫ������صĿ�


###Ȼ���ddl��β�ڷŵ�C:\mingw64\bin, ��Ϊ���ǵ�path����������ŵ����﷽����붯̬���ִ��ʱ�ҵ���ص��ļ�


###PS��b2��bjam��һ���Ķ�������������һ����صĲ���
	--build-dir=<builddir>        �������ʱ�ļ������builddir��(�����ȽϺù���������Ϳ��԰���ɾ����) 
	--stagedir=<stagedir>          ��ű������ļ���·����Ĭ����stage 
	--build-type=complete          �������а汾����Ȼֻ�����һС���ְ汾��ȷ�е�˵���൱��:variant=release, threading=multi;link=shared|static;runtime-link=shared��variant=debug|release          ��������ʲô�汾(Debug or Release?) 
	link=static|shared             ����ʹ�þ�̬�⻹�Ƕ�̬�⡣ 
	threading=single|multi         ����ʹ�õ��̻߳��Ƕ��߳̿⡣ 
	runtime-link=static|shared     �����Ǿ�̬���Ƕ�̬����C/C++��׼�⡣ 
	--with-<library>               ֻ����ָ���Ŀ⣬������--with-regex��ֻ����regex���ˡ� 
	--show-libraries               ��ʾ��Ҫ����Ŀ�����


###�ο���
	http://www.boost.org/doc/libs/1_58_0/more/getting_started/unix-variants.html
	http://blog.csdn.net/asb2010/article/details/43055391
	http://blog.csdn.net/yasi_xi/article/details/8660549
	http://www.360doc.com/content/11/0128/15/59141_89586432.shtml
	
	
	
	���ӣ��ο�scriptĿ¼�ϵ�tboost.cpp��Ȼ��ִ��
g++  -Ic:\mingw64\include\boost .\tboost.cpp -o tboost -Lc:\mingw64\bin -llibboost_regex-mgw51-mt-1_58