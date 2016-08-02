
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(200) DEFAULT NULL,
  `Number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `userPassword` varchar(50) DEFAULT NULL,
  `photo` longblob,
  `notify` tinyint(1) DEFAULT NULL,
  `facebook` tinyint(1) DEFAULT NULL,
  `GPlus` tinyint(1) DEFAULT NULL,
  `faceBookEmail` varchar(100) DEFAULT NULL,
  `gPlusEmal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;


CREATE TABLE `transportation` (
  `transportation` varchar(100) NOT NULL,
  PRIMARY KEY (`transportation`)
) ;



CREATE TABLE `category` (
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`category`)
) ;

CREATE TABLE `company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(200) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `website` varchar(200) DEFAULT NULL,
  `photo` longblob,
  `latitude` double DEFAULT NULL,
  `longtude` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;




CREATE TABLE `company_email` (
  `companyID` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`email`,`companyID`),
  KEY `companyID` (`companyID`),
  FOREIGN KEY (`companyID`) REFERENCES `company` (`ID`)
) ;

CREATE TABLE `company_location` (
  `companyID` int(11) NOT NULL,
  `location` varchar(200) NOT NULL,
  PRIMARY KEY (`location`,`companyID`),
  KEY `companyID` (`companyID`),
  CONSTRAINT `company_location_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`ID`)
) ;


CREATE TABLE `company_number` (
  `companyID` int(11) NOT NULL,
  `Number` varchar(20) NOT NULL,
  PRIMARY KEY (`Number`,`companyID`),
  KEY `companyID` (`companyID`),
  FOREIGN KEY (`companyID`) REFERENCES `company` (`ID`)
) ;



CREATE TABLE `company_rate` (
  `userID` int(11) NOT NULL,
  `companyID` int(11) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`userID`,`companyID`),
  KEY `companyID` (`companyID`),
  FOREIGN KEY (`userID`) REFERENCES `user` (`ID`),
   FOREIGN KEY (`companyID`) REFERENCES `company` (`ID`)
) ;






CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `feedBack` varchar(500) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `f1_idx` (`userID`),
  FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ;






CREATE TABLE `trips` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `companyID` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `companyName` varchar(200) DEFAULT NULL,
  `companyLocation` varchar(200) DEFAULT NULL,
  `budget` double DEFAULT NULL,
  `programme` varchar(1000) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `transportation` varchar(100) ,
  `category` varchar(100) ,
  PRIMARY KEY (`ID`,`companyID`),
  foreign key (`companyID`) references company (ID),
  foreign key (`category`) references category (category),
  foreign key (`transportation`) references transportation (transportation)
   
);

CREATE TABLE `favorite_trips` (
  `userID` int(11) NOT NULL,
  `tripID` int(11) NOT NULL,
  PRIMARY KEY (`userID`,`tripID`),
  KEY `tripID` (`tripID`),
  FOREIGN KEY (`userID`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`tripID`) REFERENCES `trips` (`ID`)
) ;

CREATE TABLE `trip_rate` (
  `userID` int(11) NOT NULL,
  `tripID` int(11) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`userID`,`tripID`),
  KEY `tripID` (`tripID`),
  FOREIGN KEY (`userID`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`tripID`) REFERENCES `trips` (`ID`)
);


CREATE TABLE `trip_photo` (
  `tripID` int(11) NOT NULL,
  `photo` longblob,
  KEY `tripID` (`tripID`),
  FOREIGN KEY (`tripID`) REFERENCES `trips` (`ID`)
) ;

CREATE TABLE `related_trips` (
  `tripID` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `photo` longblob NOT NULL,
  KEY `tripID` (`tripID`),
  CONSTRAINT `related_trips_ibfk_1` FOREIGN KEY (`tripID`) REFERENCES `trips` (`ID`)
) ;

CREATE TABLE `notification` (
  `tripID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `companyName` varchar(150) DEFAULT NULL,
  `notifyText` varchar(500) DEFAULT NULL,
  `notifyTime` varchar(50) NOT NULL,
  `photo` longblob,
  PRIMARY KEY (`notifyTime`,`tripID`),
  KEY `tripID` (`tripID`),
 FOREIGN KEY (`tripID`) REFERENCES `trips` (`ID`)
) ;


CREATE TABLE `company_trips` (
  `tripID` int(11) NOT NULL,
  `companyID` int(11) NOT NULL,
  PRIMARY KEY (`tripID`,`companyID`),
  KEY `companyID` (`companyID`),
  FOREIGN KEY (`tripID`) REFERENCES `trips` (`ID`),
   FOREIGN KEY (`companyID`) REFERENCES `company` (`ID`)
) ;