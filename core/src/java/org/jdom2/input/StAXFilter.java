package org.jdom2.input;

import org.jdom2.Namespace;

/**
 * In StAX Processing it is possible to read fragments of XML. JDOM supports
 * reading JDOM Content from StAX Readers in fragments. JDOM users can influence
 * the content that is processed by the return values in this interface.
 * <p>
 * Using the StAXStreamBuilder or StAXEventBuilder you can parse a List of
 * JDOM content by filtering that content with an instance of this filter.
 * <p>
 * There are two significant states in which methods in this interface will be
 * called:
 * <ul>
 * <li> We are not currently including any content, and we want to know whether
 *      the current StAX content should be included.
 * <li> We are currently inside an Element that this filter has indicated should
 *      be included, but perhaps you want to prune some content.
 * </ul>
 *  
 * @author Rolf Lear
 *
 */
public interface StAXFilter {

	/**
	 * The current event is a DocType event.
	 * @return true if the DocType should become a JDOM Fragment.
	 */
	public boolean includeDocType();
	
	/**
	 * The current event is an Element event.
	 * <p>
	 * If the return value of this call is true, then this Element will be 
	 * processed as a JDOM fragment. You may then get calls to the prune*
	 * methods to determine whether child content of this Element should be
	 * pruned
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param name The XML tag name of this Element
	 * @param ns The Namespace of this Element
	 * @return true if the Element should become a JDOM Fragment.
	 */
	public boolean includeElement(int depth, String name, Namespace ns);
	
	/**
	 * The current event is a Comment event.
	 * <p>
	 * A non-null return value will become the Comment text. Return null to skip
	 * the comment, the specified comment text to leave the comment unchanged,
	 * or any other String to create a different comment value.
	 * <p>
	 * To include the comment as-is, do:
	 * <br>
	 * <pre>
	 * public String includeComment(int depth, String comment) {
	 *     return comment;
	 * }
	 * </pre>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param comment The Comment value
	 * @return null if you want to exclude this comment, or a non-null value
	 *        which will become the new comment value.
	 */
	public String includeComment(int depth, String comment);
	
	/**
	 * The current event is an EntityRef event.
	 * <p>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param name The EntityRef name
	 * @return true if you want to include this EntityRef.
	 */
	public boolean includeEntityRef(int depth, String name);
	
	/**
	 * The current event is a CDATA event.
	 * <p>
	 * A non-null return value will become the CDATA text. Return null to skip
	 * the CDATA, the specified text to leave the CDATA unchanged,
	 * or any other String to create a different CDATA value.
	 * <p>
	 * To include the CDATA as-is, do:
	 * <br>
	 * <pre>
	 * public String includeCDATA(int depth, String text) {
	 *     return text;
	 * }
	 * </pre>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param text The CDATA text value
	 * @return null if you want to exclude this CDATA, or a non-null value
	 *        which will become the new CDATA text value.
	 */
	public String includeCDATA(int depth, String text);

	/**
	 * The current event is a TEXT event.
	 * <p>
	 * A non-null return value will become the Text text. Return null to skip
	 * the Text, the specified text to leave the Text unchanged,
	 * or any other String to create a different Text value.
	 * <p>
	 * To include the Text as-is, do:
	 * <br>
	 * <pre>
	 * public String includeText(int depth, String text) {
	 *     return text;
	 * }
	 * </pre>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param text The Text value
	 * @return null if you want to exclude this Text, or a non-null value
	 *        which will become the new Text value.
	 */
	public String includeText(int depth, String text);
	
	/**
	 * The current event is a ProcessingInstruction event.
	 * <p>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param target The ProcessingInstruction Target value
	 * @return true if you want to include this ProcessingInstruction.
	 */
	public boolean includeProcessingInstruction(int depth, String target);
	
	/**
	 * An Element is being included, and this is a child Element event of the
	 * included parent Element. Should this Child Element be pruned from the
	 * parent fragment?
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param name The XML tag name of this child Element
	 * @param ns The Namespace of this child Element
	 * @return true if the child Element should be excluded.
	 */
	public boolean pruneElement(int depth, String name, Namespace ns);
	
	
	/**
	 * An Element is being included, and this is a child Comment event of the
	 * included parent Element. Should this child Comment be pruned from the
	 * parent fragment?
	 * <p>
	 * A non-null return value will become the Comment value. Return null to
	 * skip the Coment, the specified comment to leave the Comment unchanged,
	 * or any other String to create a different Comment value.
	 * <p>
	 * To include the Comment as-is, do:
	 * <br>
	 * <pre>
	 * public String pruneComment(int depth, String comment) {
	 *     return comment;
	 * }
	 * </pre>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param comment The Comment value
	 * @return null if you want to exclude this Comment, or a non-null value
	 *        which will become the new Comment value.
	 */
	public String pruneComment(int depth, String comment);
	
	/**
	 * An Element is being included, and this is a child EntityRef event of the
	 * included parent Element. Should this child EntityRef be pruned from the
	 * parent fragment?
	 * <p>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param name The EntityRef name
	 * @return true if you want to exclude this EntityRef.
	 */
	public boolean pruneEntityRef(int depth, String name);

	/**
	 * An Element is being included, and this is a child CDATA event of the
	 * included parent Element. Should this child CDATA be pruned from the
	 * parent fragment?
	 * <p>
	 * A non-null return value will become the CDATA text. Return null to skip
	 * the CDATA, the specified text to leave the CDATA unchanged,
	 * or any other String to create a different CDATA value.
	 * <p>
	 * To include the CDATA as-is, do:
	 * <br>
	 * <pre>
	 * public String pruneCDATA(int depth, String text) {
	 *     return text;
	 * }
	 * </pre>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param text The CDATA text value
	 * @return null if you want to exclude this CDATA, or a non-null value
	 *        which will become the new CDATA text value.
	 */
	public String pruneCDATA(int depth, String text);
	
	/**
	 * An Element is being included, and this is a child Text event of the
	 * included parent Element. Should this child Text be pruned from the
	 * parent fragment?
	 * <p>
	 * A non-null return value will become the Text. Return null to skip
	 * the Text, the specified text to leave the Text unchanged,
	 * or any other String to create a different Text value.
	 * <p>
	 * To include the Text as-is, do:
	 * <br>
	 * <pre>
	 * public String pruneText(int depth, String text) {
	 *     return text;
	 * }
	 * </pre>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param text The Text value
	 * @return null if you want to exclude this Text, or a non-null value
	 *        which will become the new Text value.
	 */
	public String pruneText(int depth, String text);
	
	/**
	 * An Element is being included, and this is a child ProcessingInstruction
	 * event of the included parent Element. Should this ProcessingInstruction
	 * be pruned from the parent fragment?
	 * <p>
	 * @param depth The depth of this content from the document root
	 * 			(the root Element is at depth 0)
	 * @param target The ProcessingInstruction Target value
	 * @return true if you want to exclude this ProcessingInstruction.
	 */
	public boolean pruneProcessingInstruction(int depth, String target);
	
}
