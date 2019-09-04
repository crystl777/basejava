
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }


    void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                System.out.println("такое резюме уже существует");
                return;
            }
        }
        storage[size] = r;
        size++;
    }


    Resume get(String uuid) {
/*
решил обработать данное исключение здесь, а не пробрасывать его дальше, так как подумал
что лучше тесты не будут крашиться, а просто выведут в консоль информацию об ошибке.
хотя, наверно, более правильно было бы прокинуть экспешн дальше, либо вообще в данной
задаче не обрабатывать исключение и оставить всё как было :)
 */
        try {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        } catch (NullPointerException e) {
            System.out.println("такого резюме не существует");
        }
        return null;
    }


    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - (i + 1));
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println("такого резюме не существует");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[size];

        for (int i = 0; i < all.length; i++) {
            all[i] = storage[i];
        }
        return all;
    }


    int size() {
        return size;
    }
}
