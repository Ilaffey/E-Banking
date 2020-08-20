package io.ebanking.application.deposit;

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
    comments = "Source: deposit.proto")
public final class DepositGrpc {

  private DepositGrpc() {}

  public static final String SERVICE_NAME = "deposit.Deposit";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.ebanking.application.deposit.DepositRequest,
      io.ebanking.application.deposit.DepositReply> getDepositFundsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "depositFunds",
      requestType = io.ebanking.application.deposit.DepositRequest.class,
      responseType = io.ebanking.application.deposit.DepositReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.ebanking.application.deposit.DepositRequest,
      io.ebanking.application.deposit.DepositReply> getDepositFundsMethod() {
    io.grpc.MethodDescriptor<io.ebanking.application.deposit.DepositRequest, io.ebanking.application.deposit.DepositReply> getDepositFundsMethod;
    if ((getDepositFundsMethod = DepositGrpc.getDepositFundsMethod) == null) {
      synchronized (DepositGrpc.class) {
        if ((getDepositFundsMethod = DepositGrpc.getDepositFundsMethod) == null) {
          DepositGrpc.getDepositFundsMethod = getDepositFundsMethod =
              io.grpc.MethodDescriptor.<io.ebanking.application.deposit.DepositRequest, io.ebanking.application.deposit.DepositReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "depositFunds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.ebanking.application.deposit.DepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.ebanking.application.deposit.DepositReply.getDefaultInstance()))
              .setSchemaDescriptor(new DepositMethodDescriptorSupplier("depositFunds"))
              .build();
        }
      }
    }
    return getDepositFundsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DepositStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DepositStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DepositStub>() {
        @java.lang.Override
        public DepositStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DepositStub(channel, callOptions);
        }
      };
    return DepositStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DepositBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DepositBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DepositBlockingStub>() {
        @java.lang.Override
        public DepositBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DepositBlockingStub(channel, callOptions);
        }
      };
    return DepositBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DepositFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DepositFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DepositFutureStub>() {
        @java.lang.Override
        public DepositFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DepositFutureStub(channel, callOptions);
        }
      };
    return DepositFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class DepositImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void depositFunds(io.ebanking.application.deposit.DepositRequest request,
        io.grpc.stub.StreamObserver<io.ebanking.application.deposit.DepositReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositFundsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositFundsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.ebanking.application.deposit.DepositRequest,
                io.ebanking.application.deposit.DepositReply>(
                  this, METHODID_DEPOSIT_FUNDS)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class DepositStub extends io.grpc.stub.AbstractAsyncStub<DepositStub> {
    private DepositStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DepositStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DepositStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void depositFunds(io.ebanking.application.deposit.DepositRequest request,
        io.grpc.stub.StreamObserver<io.ebanking.application.deposit.DepositReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositFundsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class DepositBlockingStub extends io.grpc.stub.AbstractBlockingStub<DepositBlockingStub> {
    private DepositBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DepositBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DepositBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public io.ebanking.application.deposit.DepositReply depositFunds(io.ebanking.application.deposit.DepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositFundsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class DepositFutureStub extends io.grpc.stub.AbstractFutureStub<DepositFutureStub> {
    private DepositFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DepositFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DepositFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.ebanking.application.deposit.DepositReply> depositFunds(
        io.ebanking.application.deposit.DepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositFundsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT_FUNDS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DepositImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DepositImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSIT_FUNDS:
          serviceImpl.depositFunds((io.ebanking.application.deposit.DepositRequest) request,
              (io.grpc.stub.StreamObserver<io.ebanking.application.deposit.DepositReply>) responseObserver);
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

  private static abstract class DepositBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DepositBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.ebanking.application.deposit.DepositProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Deposit");
    }
  }

  private static final class DepositFileDescriptorSupplier
      extends DepositBaseDescriptorSupplier {
    DepositFileDescriptorSupplier() {}
  }

  private static final class DepositMethodDescriptorSupplier
      extends DepositBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DepositMethodDescriptorSupplier(String methodName) {
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
      synchronized (DepositGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DepositFileDescriptorSupplier())
              .addMethod(getDepositFundsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
