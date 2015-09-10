# MultiColumnPicker

![Android Library](https://img.shields.io/badge/platform-android-green.svg)
[ ![Download](https://api.bintray.com/packages/twiceyuan/maven/MultiColumnPicker/images/download.svg) ](https://bintray.com/twiceyuan/maven/MultiColumnPicker/_latestVersion)
[ ![WTFPL](http://img.shields.io/badge/license-WTFPL-green.svg)](http://www.wtfpl.net/txt/copying/)

a simple multiple dialog to pick linked data.

Screenshot
===

<img src="Screenshot1.png" alt="Screenshot" style="width: 180px;"/>
<img src="Screenshot2.png" alt="Screenshot" style="width: 180px;"/>
<img src="Screenshot3.png" alt="Screenshot" style="width: 180px;"/>

Sample
===

```Java
MultiColumnPicker<City, City> picker = new MultiColumnPicker<>(this); // instantiation
picker.setLeftContent(left()); // setup left content
picker.setOnLeftSelected((position, city) -> right(city)); // left selected listener
picker.setOnRightSelected((position, city) -> action(city)); // right selected listener
picker.setMapLeftString(city -> city.name); // map city to city's name
picker.setMapRightString(city -> city.name);
picker.setMapLeftId(city -> city.id); // map city to city's id
picker.setMapRightId(city -> city.id);
picker.setLeftDefault("江苏"); // set default value (left)
picker.setLeftAdapter((mapper, businesses) ->
                new CustomLeftAdapter<>(businesses, mapper)); // setup custom adapter
picker.show(); // display
```

### these methods must call before call the `show()` method

* instantiation method
* setLeftContent
* setOnLeftSelected
* setOnRightSelected
* setMapLeftString
* setMapRightString
* setMapLeftId
* setMapRightId

### these methods are optional

* setLeftDefault
* setRightAdapter
* setLeftAdapter

Read sample module to find more detail.

Usage
===

Module Gradle File

```Groovy
dependencies {
    //...
    compile 'com.twiceyuan.multicolumnpicker:library:[version code]'
}
```

License
===

               DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                       Version 2, December 2004

    Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>

    Everyone is permitted to copy and distribute verbatim or modified
    copies of this license document, and changing it is allowed as long
    as the name is changed.

               DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
      TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

     0. You just DO WHAT THE FUCK YOU WANT TO.
