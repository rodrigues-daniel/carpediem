var gulp = require('gulp');
var gutil = require('gulp-util');
var uglify = require('gulp-uglify');
var watch = require('gulp-watch');

gulp.task('scripts',function(){
        return gulp
            .src(['src/js/app.js'])
           // .pipe(uglify())
            .pipe(gulp.dest('grails-app/assets/javascripts'));


});


gulp.task('vue',function(){
    return gulp
        .src(['bower_components/vue/dist/vue.js'])
        // .pipe(uglify())
        .pipe(gulp.dest('grails-app/assets/javascripts'));


});



gulp.task('estilos',function(){
    return gulp
        .src(['src/estilos/style.css'])
        // .pipe(uglify())
        .pipe(gulp.dest('grails-app/assets/stylesheets'));


});


gulp.task('watch',function(){
    gulp.watch('src/js/**/*.js',function(event){
            gutil.log('Arquivo ' + event.path + ', Executando tasks...');
            gulp._runStep('scripts');


    });


});
