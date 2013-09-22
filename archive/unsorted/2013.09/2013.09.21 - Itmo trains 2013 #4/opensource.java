package tasks;

import lib.IO.Scanner;
import lib.collections.ComparablePair;
import lib.collections.Pair;

import java.io.PrintWriter;
import java.util.*;

public class opensource {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String inp = in.next();
        Set<String> allStudents = new HashSet<>();
        Set<String> excludedStudents = new HashSet<>();
        ArrayList<String> projects = new ArrayList<>();
        ArrayList<ArrayList<String>> students = new ArrayList<>();

        while (!inp.equals("0")) {
            String projectName = inp;

            if (!isUppercase(projectName.charAt(0)))
                throw new RuntimeException();

            projects.add(projectName);

            ArrayList<String> currentStudents = new ArrayList<>();
            String name = in.next();

            while (isLowercase(name.charAt(0))) {
                currentStudents.add(name);
                if (allStudents.contains(name)) {
                    excludedStudents.add(name);
                }

                name = in.next();
            }

            Collections.sort(currentStudents);
            ArrayList<String> uniqueStudents = new ArrayList<>();
            for (int i = 0; i < currentStudents.size() - 1; i++) {
                if (!currentStudents.get(i).equals(currentStudents.get(i + 1))) {
                    uniqueStudents.add(currentStudents.get(i));
                    allStudents.add(currentStudents.get(i));
                }
            }

            if (currentStudents.size() > 0) {
                uniqueStudents.add(currentStudents.get(currentStudents.size() - 1));
                allStudents.add(currentStudents.get(currentStudents.size() - 1));
            }

            students.add(uniqueStudents);

            inp = name;

            if (inp.equals("1")) {
                Map<String, Integer> signups = countSignups(projects, students, excludedStudents);
                output(signups, out);
                allStudents.clear();
                excludedStudents.clear();
                projects.clear();
                students.clear();
                inp = in.next();
            }

        }
    }

    Map<String, Integer> countSignups(
            ArrayList<String> projects,
            ArrayList<ArrayList<String>> students,
            Set<String> excludedStudents) {

        Map <String, Integer> res = new HashMap<>();
        for (int i = 0; i < projects.size(); i++) {
            String project = projects.get(i);
            int cnt = 0;

            for (String student : students.get(i)) {
                if (!excludedStudents.contains(student)) {
                    ++cnt;
                }
            }

            res.put(project, cnt);
        }

        return res;
    }

    void output(Map<String, Integer> signups, PrintWriter out) {
        ArrayList <ComparablePair<Integer, String>> projects = new ArrayList<>();

        for (Map.Entry entry : signups.entrySet()) {
            projects.add(new ComparablePair<>(
                    (Integer)entry.getValue(), (String)entry.getKey()));
        }

        Collections.sort(projects, new Comparator<ComparablePair<Integer, String>>() {
            @Override
            public int compare(ComparablePair<Integer, String> o1, ComparablePair<Integer, String> o2) {
                int cmp1 = o2.first.compareTo(o1.first);
                if (cmp1 != 0)
                    return cmp1;
                return o1.second.compareTo(o2.second);
            }
        });

        for (Pair<Integer, String> x : projects) {
            out.println(x.second + " " + x.first);
        }
    }

    boolean isUppercase(char c) {
        return 'A' <= c && c <= 'Z';
    }

    boolean isLowercase(char c) {
        return 'a' <= c && c <= 'z';
    }
}
