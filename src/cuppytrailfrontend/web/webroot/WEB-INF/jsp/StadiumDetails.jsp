<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <title>Title</title>
    <body>
        <h1>Stadium Details</h1>
        Stadium details for ${stadium.name} <br><br>
            Capacity: ${stadium.capacity}<br>
            Matches: <br>
            <ul>
                <c:forEach var="match" items="${stadium.matches}">
                    <li>${match.matchSummaryFormatted}</li>
                </c:forEach>
            </ul>
    <a href="../stadiums">Back to Stadium listing</a>
    </body>
</html>
