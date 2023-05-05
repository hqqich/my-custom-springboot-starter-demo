package com.tsinglink.common.utils.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;

/**
 * Created by ChenHao on 2023/4/24 is 11:24.
 *
 * @author tsinglink
 */

public class LevelDBUtil {

    //	private static final String PATH = "F:\\workspacech\\TASAD\\TASADserver\\data\\level.db";
    private static final String PATH = "./data/level.db";
    private static final File FILE = new File(PATH);
    private static final Charset CHARSET = StandardCharsets.UTF_8;


    public static synchronized void writeObject(String key, Object object) {
        Options options = new Options();
        DBFactory factory = Iq80DBFactory.factory;
        DB db = null;
        try {
            db = factory.open(FILE, options);

            WriteOptions writeOptions = new WriteOptions();
            writeOptions.snapshot(true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);

            db.put(key.getBytes(CHARSET), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static synchronized <T> Optional<T> readObject(String key, Class<T> o) {
        Options options = new Options();
        DBFactory factory = Iq80DBFactory.factory;
        DB db = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            db = factory.open(FILE, options);
            byte[] valueByte = db.get(key.getBytes(CHARSET));
            bais = new ByteArrayInputStream(valueByte);
            ois = new ObjectInputStream(bais);
            return Optional.of((T) ois.readObject());
        } catch (NullPointerException e) {
            return Optional.empty();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Optional.empty();
    }

    public static synchronized <T> List<T> readObjectList(String key, Class<T> o) {
        Options options = new Options();
        DBFactory factory = Iq80DBFactory.factory;
        DB db = null;
        try {
            db = factory.open(FILE, options);
            byte[] valueByte = db.get(key.getBytes(CHARSET));
            ByteArrayInputStream bais = new ByteArrayInputStream(valueByte);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (List<T>) ois.readObject();
        } catch (NullPointerException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static synchronized void  writeObjectList(String key, List<Object> value) {
        Options options = new Options();
        DBFactory factory = Iq80DBFactory.factory;
        DB db = null;
        try {
            db = factory.open(FILE, options);

            WriteOptions writeOptions = new WriteOptions();
            writeOptions.snapshot(true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);

            db.put(key.getBytes(CHARSET), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static synchronized void remove(String key) {
        Options options = new Options();
        DBFactory factory = Iq80DBFactory.factory;
        DB db = null;
        try {
            db = factory.open(FILE, options);
            db.delete(key.getBytes(CHARSET));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}

