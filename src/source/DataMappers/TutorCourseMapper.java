package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import source.domain.Tutor;
import source.services.MyTutorDatabase;
import source.services.TutorDatabase;

public class TutorCourseMapper {
	static Connection conn;
	static PreparedStatement ps;
	
	
	public int insertSubject(int tutorId,int subjectId) {
			
			int status = 0;
			
			try {
				conn=MyDatabaseConnection.getConn();
				ps=conn.prepareStatement("insert into tutor_has_subject values(?,?)");
				ps.setInt(1, tutorId);
				ps.setInt(2, subjectId);
				status=ps.executeUpdate();
				conn.close();
				
			}catch(Exception e){
				System.out.println(e);
				
			}
			return status;
	}
	
	
	public int deleteSubjectBySubjectId(int subjectId) {
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("delete from tutor_has_subject where subject_idsubject=?");
			ps.setInt(1, subjectId);
			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
		
	}
	
	
	public int deleteSubject(int tutorId,int subjectId) {
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("delete from tutor_has_subject where subject_idsubject=? and tutor_idtutor=?");
			ps.setInt(1, subjectId);
			ps.setInt(2, tutorId);
			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
		
	}
	
	
	public List<Tutor> getTutorForCourse(int courseId) {
		List<Tutor> tutors = new ArrayList<>();
		try {
			
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select tutor_idtutor from tutor_has_subject where subject_idsubject=(?)");
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			
			TutorDatabase mt = new MyTutorDatabase();
			while(rs.next()) {
				Tutor t = mt.getTutorAtId(rs.getInt(1));
				tutors.add(t);
			}
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return tutors;
	}


	
}
