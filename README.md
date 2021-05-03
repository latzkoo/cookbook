# Szakácskönyv

A projekt az Alkalmazásfejlesztés I. tantárgy kötelező progamjának megvalósítása.

A feladat egy szakácskönyv megvalósítása, ahol a receptek mellett a rendelkezésre álló hozzávalókat is tárolja a program, és különböző módokon segíti a felhasználókat.

## Funkciók

### Asztali alkalmazás
#### Alapanyagok kezelése

* Az alkalmazás főképernyőjén megtekinthető a rendszerben rögzített alapanyagok listája (megnevezés, minimális készlet, aktuális készlet). A lista feletti kereső mező segíti a felhasználót egy-egy hozzávaló gyorsabb megtalálásában. 
* Műveletek (hozzáadás, módosítás, törlés): Megadható a hozzávaló megnevezése, alapértelmezett mértékegysége, további mértékegysége.
* További mértékegység használata esetén meg kell adni az alapértelmezett mértékegységre történő áltváltást.

#### Minimális készlet beállítása
* Minden alapanyag adatlapján megadható egy minimális készlet az alapértelmezett mértékegységben.

#### Figyelmeztetés alacsony készletről
* Amennyiben a készlet valamely alapanyag esetében a minimális mennyiség alá csökken, azt a program jelzi a felhasználó felé a menüsor alatt található információs sávban.
* Az információs sávra történő kattintással megjeleníthető a lista azokról a hozzávalókról, amelyek esetében a minimális szint alá csökkent a készlet. 



### Webalkalmazás

#### Alapanyagok listája
* A rendszerben szereplő alapanyagok megjelenítése (megnevezés, minimális készlet, készlet). A listát szűrhetjük alacsony készlet szerint, és kereshetünk benne az alapanyagok megnevezése szerint.

#### Bevásárlás
* A bevásárlás funkcióval hozzáadhatunk alapanyagokat a készlethez (alapnyag kiválasztása, mennyiség, mennyiségi egység)
* A bevásárlást az adott alapanyag alapértelmezett mértékegységével, annak hivatalos váltó mértékegységével vagy az alapanyag adatlapján megadott további mértékegységével végezhetjük.
* A sikeres bevásárlást a rendszer a hozzáadás után jelzi. Ekkor lehetőség van a bevásárlás visszavonásra is.

#### Receptek
* A rendszerben szereplő receptek megjelenítése (megnevezés, hozzávalók száma, hozzáadás időpontja). A listában kereshetünk a recept megnevezése szerint.
* Műveletek (hozzáadás, módosítás, törlés): Megadható az étel neve, további neve(i), kategóriája, az elkészítés nehézsége, az elkészítés ideje és az adag (fő). 
  Emellett az elkészítés menetének megadásához egy hosszú leírást, valamint kép feltöltési lehetőséget is biztosít rendszer.
* A hozzávalók hozzáadását az adott alapanyag alapértelmezett mértékegységével, annak hivatalos váltó mértékegységével vagy az alapanyag adatlapján megadott további mértékegységével végezhetjük.
* A **Megtekintés** gomb megnyomásával egy modalban tekinthetjük meg a kiválasztott receptet.
* Az **Elkészítem** gomb megnyomásával egy modalban adhatjuk meg, hogy hány fő részére szeretenénk elkészíteni az adott receptet.
  Ha van készleten a recepthez szükséges hozzávalókból megfelelő mennyiség, akkor a program csökkenti a készletünket és jelzi az étel elkészítését. 
  Az alkalmazás az étel elkészítését követően ellenőrzi, hogy mely alapanyagok csökkentek a minimális szint alá. Amennyiben talál ilyen alapanyago(ka)t, akkor értesíti a felhasználót az alacsony készletről.

#### Menük
* A rendszerben szereplő menük megjelenítése (megnevezés, receptek száma, elkészítési idő, hozzáadás időpontja)
* Műveletek (hozzáadás, módosítás, törlés): Megadható a menü neve, az elkészítés ideje, az adag (fő) és kiválasztható tetszőleges mennyiségű recept.
* A **Megtekintés** gomb megnyomásával egy modalban tekinthetjük meg a kiválasztott menüt: a recepteket, a hozzávalókat, valamint az elkészítés idejét.

#### Egyszerű keresés
* Ezzel a keresővel recepteket és menüket kereshetünk név, elkészítés nehézsége, kategória és elkészítési idő szerint.

#### Alapanyag szerinti keresés
* Az alapanyag szerinti keresőben megadhatjuk, hogy hány fő részére szeretnénk ételeket elkészíteni. 
  A keresés eredményeként a rendszer megjeleníti azokat a recepteket, amelyek elkészítéséhez rendelkezésre áll megfelelő mennyiségű alapanyag, a megadott fő részére.

## Környezet

### Adatbázis

* SQLite

### Asztali alkalmazás

* JavaFX (Java 11, Maven Multi-Module Project)

### Webalkalmazás

* Java Servlet + JSP (Java 11, Maven Multi-Module Project), HTML5, CSS3 (Bootstrap 4.6), JavaScript (jQuery)

## Beállítások

* Az `application.properties` (cookbook-core/src/main/resources/application.properties) fájlban szükséges a `database.url` paraméter elérési útvonalának módosítása.

## Asztali alkalmazás indítása
``` text
mvn clean install
mvn -f cookbook-desktop javafx:run
```

## Webalkalmazás indítása

Az alkalmazás Apache Tomcat 9 szerveren, tetszőleges porton futtatható, és a böngészőben megtekinthető. Localhost esetén pl. a következő URL-en:
``` text
http://localhost/cookbook/
```

## GitHub
[https://github.com/latzkoo/cookbook](https://github.com/latzkoo/cookbook)

<br />
Budapest, 2021. május 3.