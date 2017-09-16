import java.util.Date;
import java.util.Calendar;
/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return students;
	}

	@Override
	public void setStudents(Student[] students) {
		if(students == null) {
			throw new IllegalArgumentException();
		} else {
			this.students = students;
		}
	}

	@Override
	public Student getStudent(int index) {
		if(index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			return students[index];
		}
	}

	@Override
	public void setStudent(Student student, int index) {
		if(student == null || index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			students[index] = student;
		}
	}

	@Override
	public void addFirst(Student student) {
		if(student == null) {
			throw new IllegalArgumentException();
		} else {
			students[0] = student;
		}
	}

	@Override
	public void addLast(Student student) {
		if(student == null) {
			throw new IllegalArgumentException();
		} else {
			int lastIndex = students.length - 1;
			students[lastIndex] = student;
		}
	}

	@Override
	public void add(Student student, int index) {
		if(student == null || index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			students[index] = student;
		}
	}

	@Override
	public void remove(int index) {
		if(index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			students[index] = null;
		}
	}

	@Override
	public void remove(Student student) {
		if(student == null) {
			throw new IllegalArgumentException();
		} else {
			boolean stdFound = false;

			for(int i = 0; i < students.length; i++)
			{
				if( students[i] != null && student == students[i] ){
					stdFound = true;
					students[i] = null;
					break;
				}
			}

			if(!stdFound){
				throw new IllegalArgumentException("Student not exist");
			}
		}
	}

	@Override
	public void removeFromIndex(int index) {
		if(index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			for (int i = ++index; i < students.length; i++){
				students[i] = null;
			}
		}
	}

	@Override
	public void removeFromElement(Student student) {
		if(student == null) {
			throw new IllegalArgumentException();
		} else {
			boolean stdFound = false;

			for(int i = 0; i < students.length; i++)
			{
				if(!stdFound && student == students[i] ){
					stdFound = true;
					continue;
				}

				if(stdFound){
					students[i] = null;
				}
			}
		}
	}

	@Override
	public void removeToIndex(int index) {
		if(index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++){
				if( i == index ) {
					break;
				} else {
					students[i] = null;
				}
			}
		}
	}

	@Override
	public void removeToElement(Student student) {
		if(student == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++){
				if( student == students[i] ) {
					break;
				} else {
					students[i] = null;
				}
			}
		}
	}

	@Override
	public void bubbleSort() {
		int i,j;
		Student temp;

		for (j = students.length - 1; j > 0; j--) {
			for (i = 0; i < j; i++) {
				if (students[i].getId() > students[i + 1].getId()) {
					temp = students[i];
					students[i] = students[i + 1];
					students[i + 1] = temp;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		int sCount = 1, j = 0;
		Student[] tStudents;

		if (date == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (date.equals(students[i].getBirthDate())
						|| students[i].getBirthDate().before(date)) {
					sCount++;
				}
			}

			tStudents = new Student[sCount];

			for (int i = 0; i < students.length; i++) {
				if (date.equals(students[i].getBirthDate())
						|| students[i].getBirthDate().before(date)) {
					tStudents[j++] = students[i];
				}
			}

			return tStudents;
		}
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		int sCount = 1, j = 0;
		Student[] tStudents;

		if (firstDate == null || lastDate == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if ( firstDate.equals(students[i].getBirthDate())
						|| lastDate.equals(students[i].getBirthDate())
						|| students[i].getBirthDate().after(firstDate)
						&& students[i].getBirthDate().before(lastDate)) {
					sCount++;
				}
			}

			tStudents = new Student[sCount];

			for (int i = 0; i < students.length; i++) {
				if ( firstDate.equals(students[i].getBirthDate())
						|| lastDate.equals(students[i].getBirthDate())
						|| students[i].getBirthDate().after(firstDate)
						&& students[i].getBirthDate().before(lastDate)) {
					tStudents[j++] = students[i];
				}
			}

			return tStudents;
		}
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		Date eDate, sDate;

		if (date == null) {
			throw new IllegalArgumentException();
		} else {
			sDate = date;
			Calendar c = Calendar.getInstance();
			c.setTime(sDate);
			c.add(Calendar.DATE, days);
			eDate = c.getTime();

			return this.getBetweenBirthDates(sDate, eDate);
		}
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		Calendar dob, now;
		int age;

		if( indexOfStudent == 0 ) {
			throw new IllegalArgumentException();
		} else {
			dob = Calendar.getInstance();
			now = Calendar.getInstance();
			now.setTime(new Date());
			dob.setTime(this.getStudent(indexOfStudent).getBirthDate());

			age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
			if(now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))  {
				age-=1;
			}

			return age;
		}
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
		return null;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		return null;
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		return null;
	}
}
