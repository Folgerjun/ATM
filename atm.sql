/*
Navicat MySQL Data Transfer

Source Server         : 11
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : atm123

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2017-02-23 14:10:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `account_number` int(11) default NULL,
  `branch_name` varchar(255) default NULL,
  `balance` double default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `borrower`
-- ----------------------------
DROP TABLE IF EXISTS `borrower`;
CREATE TABLE `borrower` (
  `customer_name` varchar(255) default NULL,
  `loan_number` int(11) default NULL,
  `card_number` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `branch`
-- ----------------------------
DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `branch_name` varchar(255) default NULL,
  `branch_city` varchar(255) default NULL,
  `assets` double default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_name` varchar(255) default NULL,
  `customer_street` varchar(255) default NULL,
  `customer_city` varchar(255) default NULL,
  `credit` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `depositor`
-- ----------------------------
DROP TABLE IF EXISTS `depositor`;
CREATE TABLE `depositor` (
  `customer_name` varchar(255) default NULL,
  `account_number` int(11) default NULL,
  `card_number` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `loan`
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `loan_number` int(11) default NULL,
  `branch_name` varchar(255) default NULL,
  `amount` double default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `login`
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `customer_name` varchar(255) default NULL,
  `card_style` varchar(255) default NULL,
  `card_number` int(11) default NULL,
  `password` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
