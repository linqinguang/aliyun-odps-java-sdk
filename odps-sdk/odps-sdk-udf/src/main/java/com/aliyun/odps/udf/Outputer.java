package com.aliyun.odps.udf;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.io.SinkOutputStream;

import java.io.IOException;

/**
 * Base outputer class, user-defined outputers shall extend from this class
 **/
public abstract class Outputer{

  /** Interface for setting up the outputer
   * @param ctx: the ExecutionContext which contains context information that may be useful
   *             for setting up user code execution environment
   * @param outputStream: output stream to write to
   * @param attributes: encapsulate any attributes needed that describe the associated output data and/or 
   * any other useful information for outputer
   **/
  public abstract void setup(ExecutionContext ctx, SinkOutputStream outputStream, DataAttributes attributes);

  /**
   * Interface for writing a record via output stream. Each record for output will invoke this function call,
   * The system assumes that each record can be safely disposed after output, and the system
   * might reuse the Record memory. However, user can consolidate multiple records into his/her
   * own managed memory and do a batch deserialization in one shot - if that is more desirable.
   * @param record: a record for output.
   **/
  public abstract void output(Record record) throws IOException;

  /**
   * Interface for cleaning up outputer before exit. The system will keep the physical output stream
   * open until AFTER the close() call, therefore if there is anything in-memory that the user wish to
   * output, it can still be done by invoking one of the write methods in SinkOutputStream
   **/
  public abstract void close();
}
