// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: balance.proto

package io.ebanking.application.balance;

public interface BalanceReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:balance.BalanceReply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string message = 1;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 1;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>string email = 2;</code>
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   * <code>string email = 2;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <code>string accountType = 3;</code>
   * @return The accountType.
   */
  java.lang.String getAccountType();
  /**
   * <code>string accountType = 3;</code>
   * @return The bytes for accountType.
   */
  com.google.protobuf.ByteString
      getAccountTypeBytes();

  /**
   * <code>string password = 4;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <code>string password = 4;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <code>double balance = 5;</code>
   * @return The balance.
   */
  double getBalance();
}
