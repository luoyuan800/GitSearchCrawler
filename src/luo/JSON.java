/*
 * JSON.java
 * Date: 8/6/2015
 * Time: 3:07 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package luo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSON {
    private String str;
    private Map<String, Object> values;

    private JSON(String str) {
        this.str = str;
        values = new HashMap<String, Object>();
        parse();
    }

    private void parse(){
        int start = 0;
        Stack<Integer> stack = new Stack<Integer>();
        StringBuilder key = null;
        boolean child = false;
        for(int i =0; i<str.length(); i++){
            int point = str.charAt(i);
            if(point == '{'){
                if(stack.size()!=0){
                    int j = i;
                    while(str.charAt(j)!='}'){
                        i++;
                    }
                    JSON json = new JSON(str.substring(j, i+1));
                    if(key!=null)values.put(key.toString(), json);
                    continue;
                }
            }
            if(point == ':'){
                if(stack.pop()==(int)'"') {
                    key = new StringBuilder();
                    while (stack.peek() != '"'){
                        key.append((char)stack.pop().intValue());
                    }
                    stack.pop();
                    key.reverse();
                }
                continue;
            }
            if(point == ','){
                if(stack.peek() == (int)'"' && key!=null){
                    StringBuilder v = new StringBuilder((char)stack.pop().intValue());
                    while(stack.peek()!=(int)'"'){
                        v.append((char)stack.pop().intValue());
                    }
                    stack.pop();
                    v.reverse();
                    values.put(key.toString(), v.toString());
                }
                continue;
            }
            stack.push(point);
        }
    }

    public Set<String> getKeys(){
        return values.keySet();
    }
    public Object getValue(String key){
        return values.get(key);
    }

    public static void main(String... args) {
        String str = "{\n" +
                "      \"id\": 19148949,\n" +
                "      \"name\": \"MPAndroidChart\",\n" +
                "      \"full_name\": \"PhilJay/MPAndroidChart\",\n" +
                "      \"owner\": {\n" +
                "        \"login\": \"PhilJay\",\n" +
                "        \"id\": 6759734,\n" +
                "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/6759734?v=3\",\n" +
                "        \"gravatar_id\": \"\",\n" +
                "        \"url\": \"https://api.github.com/users/PhilJay\",\n" +
                "        \"html_url\": \"https://github.com/PhilJay\",\n" +
                "        \"followers_url\": \"https://api.github.com/users/PhilJay/followers\",\n" +
                "        \"following_url\": \"https://api.github.com/users/PhilJay/following{/other_user}\",\n" +
                "        \"gists_url\": \"https://api.github.com/users/PhilJay/gists{/gist_id}\",\n" +
                "        \"starred_url\": \"https://api.github.com/users/PhilJay/starred{/owner}{/repo}\",\n" +
                "        \"subscriptions_url\": \"https://api.github.com/users/PhilJay/subscriptions\",\n" +
                "        \"organizations_url\": \"https://api.github.com/users/PhilJay/orgs\",\n" +
                "        \"repos_url\": \"https://api.github.com/users/PhilJay/repos\",\n" +
                "        \"events_url\": \"https://api.github.com/users/PhilJay/events{/privacy}\",\n" +
                "        \"received_events_url\": \"https://api.github.com/users/PhilJay/received_events\",\n" +
                "        \"type\": \"User\",\n" +
                "        \"site_admin\": false\n" +
                "      },\n" +
                "      \"private\": false,\n" +
                "      \"html_url\": \"https://github.com/PhilJay/MPAndroidChart\",\n" +
                "      \"description\": \"A powerful Android chart view / graph view library, supporting line- bar- pie- radar- bubble- and candlestick charts as well as scaling, dragging and animations.\",\n" +
                "      \"fork\": false,\n" +
                "      \"url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart\",\n" +
                "      \"forks_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/forks\",\n" +
                "      \"keys_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/keys{/key_id}\",\n" +
                "      \"collaborators_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/collaborators{/collaborator}\",\n" +
                "      \"teams_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/teams\",\n" +
                "      \"hooks_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/hooks\",\n" +
                "      \"issue_events_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/issues/events{/number}\",\n" +
                "      \"events_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/events\",\n" +
                "      \"assignees_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/assignees{/user}\",\n" +
                "      \"branches_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/branches{/branch}\",\n" +
                "      \"tags_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/tags\",\n" +
                "      \"blobs_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/git/blobs{/sha}\",\n" +
                "      \"git_tags_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/git/tags{/sha}\",\n" +
                "      \"git_refs_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/git/refs{/sha}\",\n" +
                "      \"trees_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/git/trees{/sha}\",\n" +
                "      \"statuses_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/statuses/{sha}\",\n" +
                "      \"languages_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/languages\",\n" +
                "      \"stargazers_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/stargazers\",\n" +
                "      \"contributors_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/contributors\",\n" +
                "      \"subscribers_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/subscribers\",\n" +
                "      \"subscription_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/subscription\",\n" +
                "      \"commits_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/commits{/sha}\",\n" +
                "      \"git_commits_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/git/commits{/sha}\",\n" +
                "      \"comments_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/comments{/number}\",\n" +
                "      \"issue_comment_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/issues/comments{/number}\",\n" +
                "      \"contents_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/contents/{+path}\",\n" +
                "      \"compare_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/compare/{base}...{head}\",\n" +
                "      \"merges_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/merges\",\n" +
                "      \"archive_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/{archive_format}{/ref}\",\n" +
                "      \"downloads_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/downloads\",\n" +
                "      \"issues_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/issues{/number}\",\n" +
                "      \"pulls_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/pulls{/number}\",\n" +
                "      \"milestones_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/milestones{/number}\",\n" +
                "      \"notifications_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/notifications{?since,all,participating}\",\n" +
                "      \"labels_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/labels{/name}\",\n" +
                "      \"releases_url\": \"https://api.github.com/repos/PhilJay/MPAndroidChart/releases{/id}\",\n" +
                "      \"created_at\": \"2014-04-25T14:29:47Z\",\n" +
                "      \"updated_at\": \"2015-08-06T04:31:15Z\",\n" +
                "      \"pushed_at\": \"2015-08-03T12:12:49Z\",\n" +
                "      \"git_url\": \"git://github.com/PhilJay/MPAndroidChart.git\",\n" +
                "      \"ssh_url\": \"git@github.com:PhilJay/MPAndroidChart.git\",\n" +
                "      \"clone_url\": \"https://github.com/PhilJay/MPAndroidChart.git\",\n" +
                "      \"svn_url\": \"https://github.com/PhilJay/MPAndroidChart\",\n" +
                "      \"homepage\": \"\",\n" +
                "      \"size\": 20743,\n" +
                "      \"stargazers_count\": 4691,\n" +
                "      \"watchers_count\": 4691,\n" +
                "      \"language\": \"Java\",\n" +
                "      \"has_issues\": true,\n" +
                "      \"has_downloads\": true,\n" +
                "      \"has_wiki\": true,\n" +
                "      \"has_pages\": false,\n" +
                "      \"forks_count\": 1510,\n" +
                "      \"mirror_url\": null,\n" +
                "      \"open_issues_count\": 42,\n" +
                "      \"forks\": 1510,\n" +
                "      \"open_issues\": 42,\n" +
                "      \"watchers\": 4691,\n" +
                "      \"default_branch\": \"master\",\n" +
                "      \"score\": 4.2478046\n" +
                "    }";
        JSON json = new JSON(str);
        System.out.println(json);
    }
}
