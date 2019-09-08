import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private int indexOfDuplicate = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    void save(Resume resume) {
        if (!duplicateResume(resume) && size < 10000) {
            storage[size] = resume;
            System.out.println("резюме было добавлено в storage");
            size++;
        } else if (size >= 10000) {
            System.out.println("база данных переполнена");
        }
    }

    void update(Resume resume) {
        if (duplicateResume(resume)) {
            storage[indexOfDuplicate] = resume;
            System.out.println("резюме было заменено");
        } else {
            System.out.println("такого резюме не существует");
        }
    }


    Resume get(String uuid) {
        if (duplicateResume(uuid)) {
            return storage[indexOfDuplicate];
        }
        System.out.println("такого резюме не существует");
        return null;
    }


    void delete(String uuid) {
        if (duplicateResume(uuid)) {
            System.arraycopy(storage, indexOfDuplicate + 1, storage,
                    indexOfDuplicate, size - (indexOfDuplicate + 1));
            storage[size - 1] = null;
            System.out.println("резюме удалено из storage");
            size--;
            return;
        }
        System.out.println("такого резюме не существует");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        return resumes;
    }


    private boolean duplicateResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                System.out.println("данное резюме " + resume.uuid + " уже существует");
                indexOfDuplicate = i;
                return true;
            }
        }
        return false;
    }


    private boolean duplicateResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.out.println("данное резюме " + uuid + " уже существует");
                indexOfDuplicate = i;
                return true;
            }
        }
        return false;
    }


    int size() {
        return size;
    }
}
