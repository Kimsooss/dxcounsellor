package com.example.myapplication.interf;

import com.example.myapplication.model.postModel;
import com.example.myapplication.model.repModel;
import com.example.myapplication.model.userModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommonAPI {

    static final String BASE_URL = "http://10.181.14.45:5000/";

    @POST("user")//유저등록
    Call<JsonObject> addUser(@Body userModel user);
    @GET("user/command")//유저정보 로딩
    Call<userModel> getUser(@Query("macaddr") String id);
    @PATCH("user/command")
    Call<JSONObject> editUserInfo(@Query("macaddr") HashMap postMap);
    @DELETE("user/command")
    Call<JSONObject> dropUserInfo(@Query("macaddr") String id);

    @GET("post")
    Call<ArrayList<postModel>> getPostList(@Query("macaddr") String id);
    @POST("post")
    Call<JsonObject> addPost(@Body postModel post);
    @GET("post/command")
    Call<postModel> getPost(@Query("macaddr") String postId);
    @GET("post/command")
    Call<ArrayList<postModel>> getPostPersonal(@Query("macaddr") String postId);
    @PATCH("post/command")
    Call<ArrayList<postModel>> editPost(@Query("macaddr") HashMap postMap);
    @DELETE("post/command")
    Call<ArrayList<postModel>> dropPost(@Query("macaddr") String postId);

    @GET("rep")
    Call<ArrayList<postModel>> getReplyOne(@Query("post_id") String post_id);
    @POST("rep")
    Call<repModel> addRep(@Query("macaddr") HashMap postMap);
    @GET("rep/command")
    Call<postModel> getRepDetail(@Query("rep_id") String rep_id);
    @PATCH("post/command")
    Call<ArrayList<postModel>> editrep(@Query("rep_id") HashMap postMap);
    @DELETE("post/command")
    Call<ArrayList<postModel>> droprep(@Query("rep_id") String postId);


    //유저 수정
    //유저 삭제
    //post
    //모든글 가져오기 리스트임
    //글작성
    //글 상세조회
    //글 수정
    //글 삭제
//pp.route('/rep', methods = ['GET']) #댓글 불러오기
//    def load_rep():
//    id = request.args.get('post_id', '');
//    rep_list = db.session.query(reply).filter_by(post_id = id).first()
//    if rep_list is None:
//    response = {'reply': 'empty reply' }
//        return jsonify(response) ,403
//            else:
//            return jsonify(rep_list.serialize)
//
//    @app.route('/rep', methods = ['POST'])
//    def create_rep():
//            if not request.json or not 'macaddr' in request.json :
//    abort(400)
//    post_id     = request.json.get('post_id')
//    rep_id      = request.json.get('macaddr')
//    text        = request.json.get('text')
//    like_cnt    = 0
//    create_dt   = datetime.today()
//    update_dt   = None
//
//            new_rep_obj = reply(post_id,rep_id,text,like_cnt,create_dt,update_dt)
//    db.session.add(new_rep_obj)
//            db.session.commit()
//            return jsonify(new_rep_obj.serialize ), 201
//    @app.route('/rep/command' , methods = ['GET', 'PATCH', 'DELETE'])
//    def repFuctionIf():
//            try:
//            if request.method == 'GET': ##댓글 리스트 가져오기.
//            id = request.args.get('post_id', '');
//    rep_list = db.session.query(reply).filter_by(post_id = id)
//            if rep_list is None:
//    response = {'reply': 'empty reply' }
//                return jsonify(response) ,403
//            else:
//            return jsonify(rep_list.serialize)
//    elif request.method == 'PATCH':##내댓글 수정 json
//            postid = request.json.get('post_id', '');
//    postid = request.json.get('post_id', '');
//    post_detail = db.session.query(post).filter_by(post_id = postid).one()
//    print(post_detail)
//            if 'title' in request.json :
//    post_detail.title = request.json.get('title')
//            if 'note' in request.json :
//    post_detail.note = request.json.get('note')
//            if 'category_id' in request.json :
//    post_detail.category_id = request.json.get('category_id')
//
//    post_detail.update_dt = datetime.today()
//
//            db.session.add(post_detail)
//            db.session.commit()
//
//            return jsonify(post_detail.serialize)
//
//    elif request.method == 'DELETE':##댓글 삭제
//            db.session.delete(post.query.get(id))
//            db.session.commit()
//            return jsonify( { 'result': True } )
//    except:
//            db.session.rollback()
//    raise
//#####CATEGORY

}

