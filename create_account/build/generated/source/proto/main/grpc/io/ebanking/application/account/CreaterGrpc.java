package io.ebanking.application.account;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.30.1)",
    comments = "Source: account.proto")
public final class CreaterGrpc {

  private CreaterGrpc() {}

  public static final String SERVICE_NAME = "account.Creater";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.ebanking.application.account.AccountRequest,
      io.ebanking.application.account.AccountReply> getCreateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createAccount",
      requestType = io.ebanking.application.account.AccountRequest.class,
      responseType = io.ebanking.application.account.AccountReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.ebanking.application.account.AccountRequest,
      io.ebanking.application.account.AccountReply> getCreateAccountMethod() {
    io.grpc.MethodDescriptor<io.ebanking.application.account.AccountRequest, io.ebanking.application.account.AccountReply> getCreateAccountMethod;
    if ((getCreateAccountMethod = CreaterGrpc.getCreateAccountMethod) == null) {
      synchronized (CreaterGrpc.class) {
        if ((getCreateAccountMethod = CreaterGrpc.getCreateAccountMethod) == null) {
          CreaterGrpc.getCreateAccountMethod = getCreateAccountMethod =
              io.grpc.MethodDescriptor.<io.ebanking.application.account.AccountRequest, io.ebanking.application.account.AccountReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.ebanking.application.account.AccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.ebanking.application.account.AccountReply.getDefaultInstance()))
              .setSchemaDescriptor(new CreaterMethodDescriptorSupplier("createAccount"))
              .build();
        }
      }
    }
    return getCreateAccountMethod;
  }

  // Creates a new async stub that supports all call types for the service
  public static CreaterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CreaterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CreaterStub>() {
        @java.lang.Override
        public CreaterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CreaterStub(channel, callOptions);
        }
      };
    return CreaterStub.newStub(factory, channel);
  }

 //Creates a new blocking-style stub that supports unary and streaming output calls on the service
  public static CreaterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CreaterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CreaterBlockingStub>() {
        @java.lang.Override
        public CreaterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CreaterBlockingStub(channel, callOptions);
        }
      };
    return CreaterBlockingStub.newStub(factory, channel);
  }

 //Creates a new ListenableFuture-style stub that supports unary calls on the service

  public static CreaterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CreaterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CreaterFutureStub>() {
        @java.lang.Override
        public CreaterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CreaterFutureStub(channel, callOptions);
        }
      };
    return CreaterFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class CreaterImplBase implements io.grpc.BindableService {

   
    public void createAccount(io.ebanking.application.account.AccountRequest request,
        io.grpc.stub.StreamObserver<io.ebanking.application.account.AccountReply> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAccountMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.ebanking.application.account.AccountRequest,
                io.ebanking.application.account.AccountReply>(
                  this, METHODID_CREATE_ACCOUNT)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class CreaterStub extends io.grpc.stub.AbstractAsyncStub<CreaterStub> {
    private CreaterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CreaterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CreaterStub(channel, callOptions);
    }

   
    public void createAccount(io.ebanking.application.account.AccountRequest request,
        io.grpc.stub.StreamObserver<io.ebanking.application.account.AccountReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class CreaterBlockingStub extends io.grpc.stub.AbstractBlockingStub<CreaterBlockingStub> {
    private CreaterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CreaterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CreaterBlockingStub(channel, callOptions);
    }

    
    public io.ebanking.application.account.AccountReply createAccount(io.ebanking.application.account.AccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateAccountMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class CreaterFutureStub extends io.grpc.stub.AbstractFutureStub<CreaterFutureStub> {
    private CreaterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CreaterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CreaterFutureStub(channel, callOptions);
    }

    
    public com.google.common.util.concurrent.ListenableFuture<io.ebanking.application.account.AccountReply> createAccount(
        io.ebanking.application.account.AccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ACCOUNT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CreaterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CreaterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_ACCOUNT:
          serviceImpl.createAccount((io.ebanking.application.account.AccountRequest) request,
              (io.grpc.stub.StreamObserver<io.ebanking.application.account.AccountReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CreaterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CreaterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.ebanking.application.account.AccountProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Creater");
    }
  }

  private static final class CreaterFileDescriptorSupplier
      extends CreaterBaseDescriptorSupplier {
    CreaterFileDescriptorSupplier() {}
  }

  private static final class CreaterMethodDescriptorSupplier
      extends CreaterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CreaterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CreaterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CreaterFileDescriptorSupplier())
              .addMethod(getCreateAccountMethod())
              .build();
        }
      }
    }
    return result;
  }
}
