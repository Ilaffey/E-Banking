package io.ebanking.application.balance;

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
 * service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.30.1)",
    comments = "Source: balance.proto")
public final class InquirerGrpc {

  private InquirerGrpc() {}

  public static final String SERVICE_NAME = "balance.Inquirer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.ebanking.application.balance.BalanceRequest,
      io.ebanking.application.balance.BalanceReply> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBalance",
      requestType = io.ebanking.application.balance.BalanceRequest.class,
      responseType = io.ebanking.application.balance.BalanceReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.ebanking.application.balance.BalanceRequest,
      io.ebanking.application.balance.BalanceReply> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<io.ebanking.application.balance.BalanceRequest, io.ebanking.application.balance.BalanceReply> getGetBalanceMethod;
    if ((getGetBalanceMethod = InquirerGrpc.getGetBalanceMethod) == null) {
      synchronized (InquirerGrpc.class) {
        if ((getGetBalanceMethod = InquirerGrpc.getGetBalanceMethod) == null) {
          InquirerGrpc.getGetBalanceMethod = getGetBalanceMethod =
              io.grpc.MethodDescriptor.<io.ebanking.application.balance.BalanceRequest, io.ebanking.application.balance.BalanceReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.ebanking.application.balance.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.ebanking.application.balance.BalanceReply.getDefaultInstance()))
              .setSchemaDescriptor(new InquirerMethodDescriptorSupplier("getBalance"))
              .build();
        }
      }
    }
    return getGetBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InquirerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InquirerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InquirerStub>() {
        @java.lang.Override
        public InquirerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InquirerStub(channel, callOptions);
        }
      };
    return InquirerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InquirerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InquirerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InquirerBlockingStub>() {
        @java.lang.Override
        public InquirerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InquirerBlockingStub(channel, callOptions);
        }
      };
    return InquirerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InquirerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InquirerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InquirerFutureStub>() {
        @java.lang.Override
        public InquirerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InquirerFutureStub(channel, callOptions);
        }
      };
    return InquirerFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * service definition.
   * </pre>
   */
  public static abstract class InquirerImplBase implements io.grpc.BindableService {

    
    public void getBalance(io.ebanking.application.balance.BalanceRequest request,
        io.grpc.stub.StreamObserver<io.ebanking.application.balance.BalanceReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.ebanking.application.balance.BalanceRequest,
                io.ebanking.application.balance.BalanceReply>(
                  this, METHODID_GET_BALANCE)))
          .build();
    }
  }

  /**
   * <pre>
   * The ing service definition.
   * </pre>
   */
  public static final class InquirerStub extends io.grpc.stub.AbstractAsyncStub<InquirerStub> {
    private InquirerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InquirerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InquirerStub(channel, callOptions);
    }

    
    public void getBalance(io.ebanking.application.balance.BalanceRequest request,
        io.grpc.stub.StreamObserver<io.ebanking.application.balance.BalanceReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * service definition.
   * </pre>
   */
  public static final class InquirerBlockingStub extends io.grpc.stub.AbstractBlockingStub<InquirerBlockingStub> {
    private InquirerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InquirerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InquirerBlockingStub(channel, callOptions);
    }

    
    public java.util.Iterator<io.ebanking.application.balance.BalanceReply> getBalance(
        io.ebanking.application.balance.BalanceRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }
  }

  
  public static final class InquirerFutureStub extends io.grpc.stub.AbstractFutureStub<InquirerFutureStub> {
    private InquirerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InquirerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InquirerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_BALANCE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InquirerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InquirerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((io.ebanking.application.balance.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<io.ebanking.application.balance.BalanceReply>) responseObserver);
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

  private static abstract class InquirerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InquirerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.ebanking.application.balance.BalanceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Inquirer");
    }
  }

  private static final class InquirerFileDescriptorSupplier
      extends InquirerBaseDescriptorSupplier {
    InquirerFileDescriptorSupplier() {}
  }

  private static final class InquirerMethodDescriptorSupplier
      extends InquirerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InquirerMethodDescriptorSupplier(String methodName) {
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
      synchronized (InquirerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InquirerFileDescriptorSupplier())
              .addMethod(getGetBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
